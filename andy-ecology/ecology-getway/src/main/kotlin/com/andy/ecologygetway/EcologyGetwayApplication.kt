package com.andy.ecologygetway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class EcologyGetwayApplication

fun main(args: Array<String>) {
    runApplication<EcologyGetwayApplication>(*args)
}
