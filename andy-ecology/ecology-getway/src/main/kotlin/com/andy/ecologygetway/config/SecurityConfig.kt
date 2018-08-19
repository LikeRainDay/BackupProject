package com.andy.ecologygetway.config

import org.apache.juli.WebappProperties
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * FileName: SecurityConfig
 * author:   候帅
 * data:     18/08/2018 07:25
 * Description: 进行配置安全策略文件
 * History:
 */
@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 3)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    /**
     * Web安全配置  关闭跨域访问
     * @param
     * @return
     */
    override fun configure(http: HttpSecurity?) {
        http!!
                .csrf()
                .disable()
    }
}