package com.andy.ecologyoauth2.default

import com.andy.ecologyoauth2.constant.TimeConstant
import com.andy.ecologyoauth2.dao.ClientDetailsRepository
import com.andy.ecologyoauth2.dao.GrantTypeRepository
import com.andy.ecologyoauth2.dao.ScopeRepository
import com.andy.ecologyoauth2.entity.ClientDetailsLimitEntity
import com.andy.ecologyoauth2.entity.GrantTypeEntity
import com.andy.ecologyoauth2.entity.ScopeEntity
import com.andy.ecologyoauth2.service.OAuth2DatabaseClientDetailsService
import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException
import org.springframework.security.oauth2.provider.client.BaseClientDetails
import java.util.*
import java.util.stream.Collectors

@Configuration
@Profile("default-user-and-roles_route")
class DefaultClientDetailsConfiguration: InitializingBean {

    private val log: Logger = LoggerFactory.getLogger(DefaultClientDetailsConfiguration::class.java)

    companion object {

        val DEFAULT_GRANT_TYPES = arrayOf("authorization_code", "refresh_token", "password", "client_credentials")

        val API_GRANT_TYPES =  StringUtils.join(DEFAULT_GRANT_TYPES, ",")

        val DEFAULT_SCOPE = arrayOf("read", "write", "trust")

        val API_SCOPES = StringUtils.join(DEFAULT_SCOPE, ",")
    }

    @Autowired
    private lateinit var grantTypeRepository: GrantTypeRepository

    @Autowired
    private lateinit var scopeRepository: ScopeRepository

    @Autowired
    private lateinit var oAuth2DatabaseClientDetailsService: OAuth2DatabaseClientDetailsService

    @Autowired
    private lateinit var clientDetailsRepository: ClientDetailsRepository

    override fun afterPropertiesSet() {

        val collect = Arrays
                .stream(DEFAULT_GRANT_TYPES)
                .map {
                    val grantTypeEntity = GrantTypeEntity()
                    grantTypeEntity.value = it
                    return@map grantTypeEntity
                }.collect(Collectors.toList())

        grantTypeRepository.saveAll(collect)

        if (grantTypeRepository.count().toInt() == 0){
            grantTypeRepository.saveAll(collect)
        }


        if (scopeRepository.count().toInt() == 0){

            val scopeArray = Arrays.stream(DEFAULT_SCOPE).map {
                val scopeEntity = ScopeEntity()
                scopeEntity.value = it
                return@map scopeEntity
            }.collect(Collectors.toList())

            scopeRepository.saveAll(scopeArray)
        }

        var clientDetails = BaseClientDetails("api", null, API_SCOPES, API_GRANT_TYPES, null)

        clientDetails.clientSecret = "api"
        clientDetails.registeredRedirectUri = Collections.emptySet()
        clientDetails.refreshTokenValiditySeconds = TimeConstant.REFRESH_TOKEN_SECOUND.toInt()
        clientDetails.accessTokenValiditySeconds = TimeConstant.ACCESS_TOKEN_SECOUND.toInt()

        try {
            oAuth2DatabaseClientDetailsService.addClientDetails(clientDetails)
        } catch (e: ClientAlreadyExistsException) {
            log.warn(e.message)
        }

        clientDetails = BaseClientDetails("open_api", null, API_SCOPES, API_GRANT_TYPES, null)
        clientDetails.clientSecret = "open_api"
        clientDetails.registeredRedirectUri = Collections.emptySet()

        try {
            oAuth2DatabaseClientDetailsService.addClientDetails(clientDetails)
            val detailsEntity = clientDetailsRepository.findOneByClientId(clientDetails.clientId).get()
            val limitEntity = ClientDetailsLimitEntity()
            limitEntity.intervalInMills = 100000L
            limitEntity.limits = 3L
            detailsEntity.clientLimit = limitEntity
            limitEntity.clientDetail = detailsEntity
            clientDetailsRepository.save(detailsEntity)
        } catch (e: Exception) {
            log.warn(e.message)
        }
    }
}