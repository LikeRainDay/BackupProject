package com.andy.ecologyconfig

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.config.server.EnableConfigServer

@EnableConfigServer
@SpringBootApplication
@EnableDiscoveryClient
class EcologyConfigApplication

fun main(args: Array<String>) {
    runApplication<EcologyConfigApplication>(*args)
}
