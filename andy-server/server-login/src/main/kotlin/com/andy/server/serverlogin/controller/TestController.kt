package com.andy.server.serverlogin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/hello")
    fun helloWorld(): String {
        return "访问成功"
    }
}