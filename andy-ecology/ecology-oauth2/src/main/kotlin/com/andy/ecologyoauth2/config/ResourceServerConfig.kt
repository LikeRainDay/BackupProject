package com.andy.ecologyoauth2.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

/**
 * FileName: ResourceServerConfig
 * author:   候帅
 * data:     18/08/2018 07:38
 * Description: 资源服务配置
 * History:
 */
@Configuration
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http!!
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/health", "/user", "/oauth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
    }
}