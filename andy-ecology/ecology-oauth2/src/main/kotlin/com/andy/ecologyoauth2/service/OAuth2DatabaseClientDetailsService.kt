package com.andy.ecologyoauth2.service

import com.andy.ecologyoauth2.dao.ClientDetailsRepository
import com.andy.ecologyoauth2.dao.GrantTypeRepository
import com.andy.ecologyoauth2.dao.ResourceIdRepository
import com.andy.ecologyoauth2.dao.ScopeRepository
import com.andy.ecologyoauth2.entity.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.*
import org.springframework.security.oauth2.provider.client.BaseClientDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Collectors.*

@Service
class OAuth2DatabaseClientDetailsService: ClientDetailsService, ClientRegistrationService {

    private val log: Logger = LoggerFactory.getLogger(OAuth2DatabaseClientDetailsService::class.java)

    @Autowired
    private lateinit var clientDetailsRepository: ClientDetailsRepository

    @Autowired
    private lateinit var grantTypeRepository: GrantTypeRepository

    @Autowired
    private lateinit var scopeRepository: ScopeRepository

    @Autowired
    private lateinit var resourceIdRepository: ResourceIdRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder


    override fun loadClientByClientId(clientId: String?): ClientDetails {
        return clientDetailsRepository.findOneByClientId(clientId!!).map {
            return@map baseClientDetails(it)
        }.orElseThrow {
            NoSuchClientException("Client ID not found")
        }
    }

    override fun listClientDetails(): MutableList<ClientDetails> {
        return clientDetailsRepository.findAll().stream().map {
            return@map baseClientDetails(it)
        }.collect(Collectors.toList())
    }

    override fun updateClientSecret(clientId: String?, secret: String?) {
        val clientDetailsEntity = clientDetailsRepository.findOneByClientId(clientId!!).orElseThrow { NoSuchClientException("Client id not found") }
//        clientDetailsEntity.clientSecret = secret!!
        clientDetailsEntity.clientSecret = passwordEncoder.encode(secret)

        clientDetailsRepository.save(clientDetailsEntity)
    }

    override fun removeClientDetails(clientId: String?) {
        val entityToRemove = clientDetailsRepository.findOneByClientId(clientId!!).orElseThrow { NoSuchClientException("Client id not found") }
        clientDetailsRepository.delete(entityToRemove)
    }

    @Transactional
    override fun addClientDetails(clientDetails: ClientDetails?) {

        if (clientDetailsRepository.findOneByClientId(clientDetails!!.clientId).isPresent){
            throw ClientAlreadyExistsException("Client ID already exists")
        }
        val clientDetailsEntity = ClientDetailsEntity()
        clientDetailsEntity.clientId = clientDetails.clientId
        clientDetailsEntity.clientSecret = clientDetails.clientSecret
        clientDetailsEntity.accessTokenValiditySeconds = clientDetails.accessTokenValiditySeconds
        clientDetailsEntity.refreshTokenValiditySeconds = clientDetails.refreshTokenValiditySeconds

        clientDetailsEntity.authorizedGrantTypeXrefs = clientDetails.authorizedGrantTypes.stream().map {
            return@map grantTypeRepository.findOneByValue(it).map {
                val clientDetailToAuthorizedGrantTypeXrefEntity = ClientDetailsToAuthorizedGrantTypeXrefEntity()
                clientDetailToAuthorizedGrantTypeXrefEntity.clientDetails = clientDetailsEntity
                clientDetailToAuthorizedGrantTypeXrefEntity.grantType = it
                return@map clientDetailToAuthorizedGrantTypeXrefEntity
            }.orElseThrow {
                ClientRegistrationException("Unsupported grant type: $it")
            }
        }.collect(toSet())

        clientDetailsEntity.scopeXrefs = clientDetails.scope.stream().map {
            val scope = it
            scopeRepository.findOneByValue(it).map {
                val clientDetailsToScopeXrefEntity = ClientDetailsToScopeXrefEntity()
                clientDetailsToScopeXrefEntity.clientDetails = clientDetailsEntity
                clientDetailsToScopeXrefEntity.scope = it
                clientDetailsToScopeXrefEntity.autoApprove = clientDetails.isAutoApprove(scope)
                return@map clientDetailsToScopeXrefEntity
            }.orElseThrow {
                ClientRegistrationException("Unsupported scope type: $it")
            }
        }.collect(toSet())

        clientDetailsEntity.resourceIdXrefs = clientDetails.resourceIds.stream().map {
            resourceIdRepository.findOneByValue(it).map {
                val clientDetailsToResourceIdXrefEntity = ClientDetailsToResourceIdXrefEntity()
                clientDetailsToResourceIdXrefEntity.clientDetails = clientDetailsEntity
                clientDetailsToResourceIdXrefEntity.resourceId = it
                return@map clientDetailsToResourceIdXrefEntity
            }.orElseThrow {
                ClientRegistrationException("Unsupported resource type: $it")
            }
        }.collect(toSet())

        clientDetailsEntity.redirectUris = clientDetails.registeredRedirectUri.stream().map {
            val redirectUrlEntity = RedirectUrlEntity()
            redirectUrlEntity.clientDetails =clientDetailsEntity
            redirectUrlEntity.value = it
            return@map redirectUrlEntity
        }.collect(toSet())

        clientDetailsRepository.save(clientDetailsEntity)
    }

    @Transactional
    override fun updateClientDetails(clientDetails: ClientDetails?) {

        val entity = clientDetailsRepository.findOneByClientId(clientDetails!!.clientId).orElseThrow { NoSuchClientException("Client details not found") }

        entity.accessTokenValiditySeconds = clientDetails.accessTokenValiditySeconds
        entity.refreshTokenValiditySeconds = clientDetails.refreshTokenValiditySeconds

        val grantTypeXrefEntityRemoves = entity.authorizedGrantTypeXrefs.stream().filter {
           !clientDetails.authorizedGrantTypes.contains(it.grantType.value)
        }.collect(toSet())

        val grantTypeOrgionValueSet = entity.authorizedGrantTypeXrefs.stream().map {
            it.grantType.value
        }.collect(toSet())

        val grantTypeXrefEntityNewOnes = clientDetails.authorizedGrantTypes.stream().filter {
            grantTypeOrgionValueSet.contains(it)
        }
                .map {
                    grantTypeRepository.findOneByValue(it)
                            .map {
                                val xrefEntity = ClientDetailsToAuthorizedGrantTypeXrefEntity()
                                xrefEntity.clientDetails = entity
                                xrefEntity.grantType = it
                                return@map xrefEntity
                            }.orElseThrow {
                                ClientRegistrationException("Unsupported grant type: $it")
                            }
                }
                .collect(toSet())

        entity.authorizedGrantTypeXrefs.removeAll(grantTypeXrefEntityRemoves)
        entity.authorizedGrantTypeXrefs.addAll(grantTypeXrefEntityNewOnes)

        val scopeXrefEntityRemoves = entity.scopeXrefs.stream().filter {
            !clientDetails.scope.contains(it.scope.value)
        }.collect(toSet())

        val scopeOriginValueSet = entity.scopeXrefs.stream().map {
            return@map it.scope.value
        }.collect(toSet())

        val scopeXrefEntityNewOns = clientDetails.scope.stream().filter {
            scopeOriginValueSet.contains(it)
        }.map {
            val scope = it
            scopeRepository.findOneByValue(it)
                    .map {
                        val xrefEntity = ClientDetailsToScopeXrefEntity()
                        xrefEntity.clientDetails = entity
                        xrefEntity.scope = it
                        xrefEntity.autoApprove = clientDetails.isAutoApprove(scope)
                        return@map xrefEntity
                    }.orElseThrow {
                        ClientRegistrationException("Unsupported scope type: $it")
                    }
        }.collect(toSet())

        entity.scopeXrefs.removeAll(scopeXrefEntityRemoves)
        entity.scopeXrefs.forEach {
            it.autoApprove = clientDetails.isAutoApprove(it.scope.value)
        }
        entity.scopeXrefs.addAll(scopeXrefEntityNewOns)


        val resourceIdXrefEntityRemoves = entity.resourceIdXrefs.stream().filter {
            !clientDetails.resourceIds.contains(it.resourceId.value)
        }.collect(toSet())

        val resourceIdOriginValueSet = entity.resourceIdXrefs.stream().map {
            return@map it.resourceId.value
        }.collect(toSet())

        val resourceIdEntityNewOnes = clientDetails.resourceIds.stream().filter {
            !resourceIdOriginValueSet.contains(it)
        }.map {
            resourceIdRepository.findOneByValue(it)
                    .map {
                        val xrefEntity = ClientDetailsToResourceIdXrefEntity()
                        xrefEntity.clientDetails = entity
                        xrefEntity.resourceId = it
                        return@map xrefEntity
                    }.orElseThrow {
                        ClientRegistrationException("Unsupported resource type: $it")
                    }
        }.collect(toSet())

        entity.resourceIdXrefs.removeAll(resourceIdXrefEntityRemoves)
        entity.resourceIdXrefs.addAll(resourceIdEntityNewOnes)


        val redirectUriEntityRemoves = entity.redirectUris.stream().filter {
            !clientDetails.registeredRedirectUri.contains(it.value)
        }.collect(toSet())

        val originRedirectUrisValue = entity.redirectUris.stream().map {
            return@map it.value
        }.collect(toSet())

        val redirectUrlEntityNewOnes = clientDetails.registeredRedirectUri.stream().filter {
            !originRedirectUrisValue.contains(it)
        }.map {
            val urlEntity = RedirectUrlEntity()
            urlEntity.clientDetails = entity
            urlEntity.value = it
            return@map urlEntity
        }.collect(Collectors.toSet())

        entity.redirectUris.removeAll(redirectUriEntityRemoves)
        entity.redirectUris.addAll(redirectUrlEntityNewOnes)

        clientDetailsRepository.save(entity)
    }


    private fun baseClientDetails(it: ClientDetailsEntity): BaseClientDetails {
        val clientDetails = BaseClientDetails()
        clientDetails.clientId = it.clientId
        clientDetails.clientSecret = it.clientSecret
        clientDetails.accessTokenValiditySeconds = it.accessTokenValiditySeconds
        clientDetails.refreshTokenValiditySeconds = it.refreshTokenValiditySeconds

        clientDetails.setAuthorizedGrantTypes(it.authorizedGrantTypeXrefs.stream().map {
            return@map it.grantType.value
        }.collect(toList()))

        clientDetails.setScope(it.scopeXrefs.stream().map {
            return@map it.scope.value
        }.collect(toList()))

        clientDetails.setAutoApproveScopes(it.scopeXrefs.stream().filter({
            return@filter it.autoApprove
        }).map {
            return@map it.scope.value
        }.collect(toList()))

        clientDetails.setResourceIds(it.resourceIdXrefs.stream().map {
            return@map it.resourceId.value
        }.collect(toList()))

        clientDetails.registeredRedirectUri = it.redirectUris.stream().map {
            return@map it.value
        }.collect(toSet())

        clientDetails.additionalInformation = Collections.emptyMap()

        return clientDetails
    }

}