package com.andy.service.servierusercenter.controller

import com.andy.andycommonbean.controller.BaseController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api"])
class HealthController : BaseController() {

    private val log: Logger = LoggerFactory.getLogger(HealthController::class.java)

    @GetMapping("/health")
    fun health(): String {
        return "ok"
    }

}