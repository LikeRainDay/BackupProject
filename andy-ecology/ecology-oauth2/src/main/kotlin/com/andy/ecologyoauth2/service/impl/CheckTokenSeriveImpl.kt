package com.andy.ecologyoauth2.service.impl

import com.andy.ecologyoauth2.service.CheckTokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.stereotype.Service


@Service
class CheckTokenSeriveImpl : CheckTokenService {

    @Autowired
    private lateinit var tokenStore: TokenStore

    override fun checkToken(token: String): Any {
        val readAccessToken = tokenStore.readAccessToken(token)
                ?: throw InvalidTokenException("Token was not recognised")
        if (readAccessToken.isExpired)
            throw IllegalAccessException("Token has expired")
        val readAuthentication = tokenStore.readAuthentication(readAccessToken)
        return readAuthentication.principal
    }

}