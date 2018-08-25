package com.andy.service.servierusercenter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("com.andy")
class ServierUserCenterApplication

fun main(args: Array<String>) {
    runApplication<ServierUserCenterApplication>(*args)
}
