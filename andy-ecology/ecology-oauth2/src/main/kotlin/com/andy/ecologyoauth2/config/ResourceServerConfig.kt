package com.andy.ecologyoauth2.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import javax.servlet.http.HttpServletResponse

/**
 * FileName: ResourceServerConfig
 * author:   候帅
 * data:     18/08/2018 07:38
 * Description: 资源服务配置
 * History:
 */
@Configuration
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter(){

    override fun configure(http: HttpSecurity?) {
//        http!!
//                .anonymous()
//                .disable()
//                .requestMatchers()
//                .antMatchers("/api/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/**")
//                .fullyAuthenticated()
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(OAuth2AccessDeniedHandler())
        http!!
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint { request, response, authException ->
                    response
                            .sendError(HttpServletResponse.SC_UNAUTHORIZED)
                }
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
    }
}