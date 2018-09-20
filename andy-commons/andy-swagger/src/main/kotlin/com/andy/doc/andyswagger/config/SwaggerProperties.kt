package com.andy.doc.andyswagger.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "swagger.deploy")
class SwaggerProperties {

    var apiPackage: String = "com.andy"

    var title: String = "接口文档内容"

    var des: String = "默认文档配置"

    var version: String = "1.0.0"

    var author: String = "候帅"

    var email: String = "houshuai0816@126.com"

    var web: String = "www.google.com"
}