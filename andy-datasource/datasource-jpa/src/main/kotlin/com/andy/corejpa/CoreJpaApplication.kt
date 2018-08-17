package com.andy.corejpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoreJpaApplication

fun main(args: Array<String>) {
    runApplication<CoreJpaApplication>(*args)
}
