package com.andy.ecologyoauth2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
//@EnableOAuth2Sso
class EcologyOauth2Application

fun main(args: Array<String>) {
    runApplication<EcologyOauth2Application>(*args)
}
