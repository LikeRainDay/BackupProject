package com.andy.ecologyoauth2.config

import com.andy.ecologyoauth2.service.impl.DomainUserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

/**
 * FileName: SecurityConfig
 * author:   候帅
 * data:     18/08/2018 07:39
 * Description: 安全策略配置
 * History:
 */
@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter(){

    @Bean
    override fun userDetailsService(): UserDetailsService {
        return DomainUserDetailsServiceImpl()
    }
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService())
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }


//    override fun configure(http: HttpSecurity?) {
//        http!!
//                .anonymous()
//                .disable()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//    }
}