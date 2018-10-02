package com.andy.ecologyoauth2.security

import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val log = LoggerFactory.getLogger(CustomAuthenticationEntryPoint::class.java)

    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, ae: AuthenticationException?) {
        log.info("进行检测授权")

        response!!.sendError(HttpServletResponse.SC_UNAUTHORIZED, ae?.message)
    }
}