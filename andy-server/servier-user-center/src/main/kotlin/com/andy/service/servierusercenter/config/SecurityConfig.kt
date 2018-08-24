package com.andy.service.servierusercenter.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityConfig {

    /**
     * describe: 设置加密方式
     * author 候帅
     * date 2018/8/24 上午9:03
     */
    @Bean
    fun bCryptPassword(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}