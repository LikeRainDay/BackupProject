package com.andy.ecologyoauth2.config

import com.andy.ecologyoauth2.service.impl.DataBaseUserDetailsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationTrustResolverImpl
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


/**
 * FileName: WebSecurityConfig
 * author:   候帅
 * data:     18/08/2018 07:39
 * Description: 安全策略配置
 * History:
 */
@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter(){

    private var log: Logger = LoggerFactory.getLogger(WebSecurityConfig::class.java)

    @Bean
    fun userDetail():UserDetailsService {
        return DataBaseUserDetailsService()
    }


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!
                .userDetailsService(userDetail())
                .passwordEncoder(passwordEncoder())
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity?) {
        http!!
                .httpBasic()
                .and()
                .csrf()
                .disable()
//        http!!
//                .authorizeRequests()
//                .antMatchers("/", "/auth/**", "/api/health", "/oauth/**", "/default/**", "/login","/user")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
    }

    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers("/resources/**")
    }

    /**
     * describe: 返回对应的用户名
     * author 候帅
     * date 2018/8/19 上午9:28
     * @return 用户名
     */
    @Bean
    fun auditorAwareBean(): String {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication == null || AuthenticationTrustResolverImpl().isAnonymous(authentication))
            return "@SYSTEM"
        val principal = authentication.principal
        return when (principal) {
            is String -> principal
            is UserDetails -> principal.username
            else -> principal.toString()
        }
    }
}