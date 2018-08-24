package com.andy.server.serveremail

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServerEmailApplication

fun main(args: Array<String>) {
    runApplication<ServerEmailApplication>(*args)
}
