package com.andy.coreredis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoreRedisApplication

fun main(args: Array<String>) {
    runApplication<CoreRedisApplication>(*args)
}
