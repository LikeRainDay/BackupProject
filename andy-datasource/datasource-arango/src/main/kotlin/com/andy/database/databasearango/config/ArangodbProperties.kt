package com.andy.database.databasearango.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

/**
 * FileName: ArangodbProperties
 * author:   候帅
 * data:     26/09/2018 21:59
 * Description: Arangodb的属性配置
 * History:
 */
@Component
@PropertySource(value = ["classpath:rangodb.yml"])
@ConfigurationProperties(prefix = "arangodb")
class ArangodbProperties {

    // hosts 地址
    var hosts: String = "127.0.0.1"

    // 用户信息
    lateinit var user: String

    // 用户密码
    lateinit var password: String

    // 端口
    var port: Int = 8529

    // 数据源
    lateinit var database: String
}