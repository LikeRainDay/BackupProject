package com.andy.service.servierusercenter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = ["com.andy"])
class ServierUserCenterApplication

fun main(args: Array<String>) {
    runApplication<ServierUserCenterApplication>(*args)


}
