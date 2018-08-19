package com.andy.ecologyoauth2.security

import com.andy.ecologyoauth2.service.DatabaseTokenStoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomLogoutSuccessHandler: AbstractAuthenticationTargetUrlRequestHandler(), LogoutSuccessHandler {

    companion object {
        // 后面有一个空格
        const val BEARER_AUTHENTICATION = "Bearer "

        const val HEADER_AUTHORIZATION = "authorization"
    }

    @Autowired
    private lateinit var tokenStoreService: DatabaseTokenStoreService

    override fun onLogoutSuccess(request: HttpServletRequest?, response: HttpServletResponse?, p2: Authentication?) {

        val token = request!!.getHeader(HEADER_AUTHORIZATION)

        if (token != null && token.startsWith(BEARER_AUTHENTICATION)){
            val oAuth2AccessToken = tokenStoreService.readAccessToken(token.split(" ")[0])

            if (!ObjectUtils.isEmpty(oAuth2AccessToken))
                tokenStoreService.removeAccessToken(oAuth2AccessToken)

        }
        response!!.status = HttpServletResponse.SC_OK
    }


}