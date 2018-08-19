package com.andy.ecologyoauth2.service

import com.andy.ecologyoauth2.dao.AccessTokenRepository
import com.andy.ecologyoauth2.dao.RefreshTokenRepository
import com.andy.ecologyoauth2.entity.AccessTokenEntity
import com.andy.ecologyoauth2.entity.RefreshTokenEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2RefreshToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

/**
 * describe: 自定义Token存储
 * author 候帅
 * date 2018/8/18 下午10:05
 */
@Service
@Transactional
class DatabaseTokenStoreService: TokenStore {

    @Autowired
    private lateinit var accessTokenRepository: AccessTokenRepository

    @Autowired
    private lateinit var refreshTokenRepository: RefreshTokenRepository

    private val authenticationKeyGenerator: AuthenticationKeyGenerator = DefaultAuthenticationKeyGenerator()

    override fun readAuthenticationForRefreshToken(token: OAuth2RefreshToken?): OAuth2Authentication {
        return readAuthentication(token?.value)
    }

    override fun readAuthentication(token: String?): OAuth2Authentication {
        return accessTokenRepository.findOneByTokenId(token!!).map {
            return@map it.authentication
        }.orElse(null)
    }

    override fun storeAccessToken(token: OAuth2AccessToken?, authentication: OAuth2Authentication?) {

        val tokenId = token!!.value

        val refreshToken: RefreshTokenEntity?

        val authenticationKey = authenticationKeyGenerator.extractKey(authentication)

        if (token.refreshToken != null){
            refreshToken = refreshTokenRepository
                    .findOneByTokenId(token.refreshToken.value)
                    .orElseGet {
                        val refreshTokenEntity = RefreshTokenEntity()
                        refreshTokenEntity.tokenId = token.refreshToken.value
                        refreshTokenEntity.token = token.refreshToken
                        refreshTokenEntity.authentication = authentication!!

                        refreshTokenRepository
                                .save(refreshTokenEntity)
                    }
        }else{
            refreshToken = null
        }

        accessTokenRepository.findOneByAuthenticationId(authenticationKey).ifPresent {
            if (tokenId != it.tokenId)
                accessTokenRepository.delete(it)
        }

        val entityToSave = accessTokenRepository.findOneByTokenId(tokenId = tokenId).map {
            it.token = token
            it.authenticationId = authenticationKey
            it.authentication = authentication!!
            it.userName = authentication.name
            it.clientId = authentication.oAuth2Request.clientId
            it.refreshToken = refreshToken!!
            return@map it
        }.orElseGet {
            val accessTokenEntity = AccessTokenEntity()
            accessTokenEntity.tokenId = tokenId
            accessTokenEntity.token = token
            accessTokenEntity.authenticationId = authenticationKey
            accessTokenEntity.authentication = authentication!!
            accessTokenEntity.userName = authentication.name
            accessTokenEntity.clientId = authentication.oAuth2Request.clientId
            accessTokenEntity.refreshToken = refreshToken!!
            return@orElseGet accessTokenEntity
        }

        accessTokenRepository.save(entityToSave)

    }

    override fun readAuthentication(token: OAuth2AccessToken?): OAuth2Authentication {
        return readAuthentication(token?.value)
    }

    override fun readRefreshToken(tokenValue: String?): OAuth2RefreshToken {
        return refreshTokenRepository.findOneByTokenId(tokenValue!!).map {
            return@map it.token
        }.orElse(null)
    }

    override fun findTokensByClientId(clientId: String?): MutableCollection<OAuth2AccessToken> {
        return accessTokenRepository.findAllByClientId(clientId!!).stream().map {
            return@map it.token
        }.collect(Collectors.toList())
    }

    override fun removeRefreshToken(token: OAuth2RefreshToken?) {
        refreshTokenRepository.deleteByTokenId(token?.value!!)
    }

    override fun removeAccessTokenUsingRefreshToken(refreshToken: OAuth2RefreshToken?) {
        accessTokenRepository.deleteByRefreshTokenTokenId(refreshToken!!.value)
    }

    override fun readAccessToken(tokenValue: String?): OAuth2AccessToken {
        return accessTokenRepository.findOneByTokenId(tokenValue!!).map {
            return@map it.token
        }.orElse(null)
    }

    override fun storeRefreshToken(refreshToken: OAuth2RefreshToken?, authentication: OAuth2Authentication?) {
        val entityToSave = refreshTokenRepository.findOneByTokenId(refreshToken!!.value).map {
            it.token = refreshToken
            it.authentication = authentication!!
            return@map it
        }.orElseGet {
            val refreshTokenEntity = RefreshTokenEntity()
            refreshTokenEntity.tokenId = refreshToken.value
            refreshTokenEntity.authentication = authentication!!
            refreshTokenEntity.token = refreshToken
            return@orElseGet refreshTokenEntity
        }

        refreshTokenRepository.save(entityToSave)
    }

    override fun getAccessToken(authentication: OAuth2Authentication?): OAuth2AccessToken {
        val authenticationKey = authenticationKeyGenerator.extractKey(authentication)
        return accessTokenRepository.findOneByAuthenticationId(authenticationKey).map {
            return@map it.token
        }.orElse(null)
    }

    override fun findTokensByClientIdAndUserName(clientId: String?, userName: String?): MutableCollection<OAuth2AccessToken> {
        return accessTokenRepository.findAllByClientIdAndUserName(clientId!!, userName!!).stream().map {
            return@map it.token
        }.collect(Collectors.toList())
    }

    override fun removeAccessToken(token: OAuth2AccessToken?) {
        accessTokenRepository.deleteByTokenId(token?.value!!)
    }
}