package com.andy.database.coreelasticsearch

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "spring.elasticsearch.jest")
class EsClientConfiguration {

    lateinit var url: String

    lateinit var password: String

    lateinit var username: String

    var readTimeout: Int = 30000

    var connectionTimeout: Int = 30000

}