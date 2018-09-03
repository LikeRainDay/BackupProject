package com.andy.ecologyoauth2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = ["com.andy"])
class EcologyOauth2Application

fun main(args: Array<String>) {
    runApplication<EcologyOauth2Application>(*args)
}
