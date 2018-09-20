package com.andy.service.servierusercenter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = ["com.andy"])
@ComponentScan(value = ["com.andy"])
@EnableSwagger2
class ServierUserCenterApplication

fun main(args: Array<String>) {
    runApplication<ServierUserCenterApplication>(*args)


}
