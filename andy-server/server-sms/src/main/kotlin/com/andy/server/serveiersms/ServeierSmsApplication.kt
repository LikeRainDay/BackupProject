package com.andy.server.serveiersms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class ServeierSmsApplication

fun main(args: Array<String>) {
    runApplication<ServeierSmsApplication>(*args)
}
