package com.andy.server.serveremail

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class ServerEmailApplication

fun main(args: Array<String>) {
    runApplication<ServerEmailApplication>(*args)
}
