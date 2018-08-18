package com.andy.ecologyoauth2.config

import com.andy.ecologyoauth2.service.DomainUserDetailsService
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension

/**
 * FileName: SecurityConfig
 * author:   候帅
 * data:     18/08/2018 07:39
 * Description: 安全策略配置
 * History:
 */
@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
class SecurityConfig : WebSecurityConfigurerAdapter(){

    @Bean
    override fun userDetailsService(): UserDetailsService {
        return DomainUserDetailsService()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
    }

    @Bean
    fun securityEvaluationContextExtension(): SecurityEvaluationContextExtension {
        return SecurityEvaluationContextExtension()
    }


    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}