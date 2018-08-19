package com.andy.ecologyoauth2.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationEntryPoint: AuthenticationEntryPoint {

    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, ae: AuthenticationException?) {
        response!!.sendError(HttpServletResponse.SC_UNAUTHORIZED, ae?.message)
    }
}