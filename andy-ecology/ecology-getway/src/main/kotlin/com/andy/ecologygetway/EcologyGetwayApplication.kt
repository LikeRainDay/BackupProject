package com.andy.ecologygetway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
// 开启OAuth2的单点登录
@EnableOAuth2Sso
@EnableFeignClients(basePackages = ["com.andy"])
class EcologyGetwayApplication

fun main(args: Array<String>) {
    runApplication<EcologyGetwayApplication>(*args)
}
