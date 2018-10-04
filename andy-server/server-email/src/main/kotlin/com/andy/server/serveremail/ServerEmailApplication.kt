package com.andy.server.serveremail

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableAsync
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = ["com.andy"])
@EnableSwagger2
@EnableAsync
class ServerEmailApplication

fun main(args: Array<String>) {
    runApplication<ServerEmailApplication>(*args)
}
