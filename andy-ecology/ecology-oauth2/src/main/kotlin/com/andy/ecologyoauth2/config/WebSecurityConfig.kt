package com.andy.ecologyoauth2.config

import com.andy.ecologyoauth2.service.DataBaseUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationTrustResolverImpl
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler

/**
 * FileName: WebSecurityConfig
 * author:   候帅
 * data:     18/08/2018 07:39
 * Description: 安全策略配置
 * History:
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER - 2)
class WebSecurityConfig : WebSecurityConfigurerAdapter(){

    @Autowired
    private lateinit var userDetailsService: DataBaseUserDetailsService

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }


    override fun configure(http: HttpSecurity?) {
        http!!
                .exceptionHandling()
                .accessDeniedPage("/login.html?authorization_error=true")
                .and()
                .logout()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
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

    companion object {
        /**
         * describe: 全局方法安全配置
         * author 候帅
         * date 2018/8/19 上午9:31
         */
        @EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
        class GlobalSecurityConfiguration: GlobalMethodSecurityConfiguration(){

            override fun createExpressionHandler(): MethodSecurityExpressionHandler {
                return OAuth2MethodSecurityExpressionHandler()
            }
        }

    }

}