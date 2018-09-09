package com.andy.ecologygetway.zuul.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api"])
class HealthController {

    private val log: Logger = LoggerFactory.getLogger(HealthController::class.java)

    @GetMapping("/health")
    fun health(): String {
        return "ok"
    }

}