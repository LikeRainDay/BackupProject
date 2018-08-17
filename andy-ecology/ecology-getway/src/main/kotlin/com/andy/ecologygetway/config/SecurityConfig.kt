package com.andy.ecologygetway.config

import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties
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
@Order(1000)
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