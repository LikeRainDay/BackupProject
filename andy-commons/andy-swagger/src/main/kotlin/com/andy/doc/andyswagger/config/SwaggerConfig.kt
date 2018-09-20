package com.andy.doc.andyswagger.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfig {

    @Autowired
    private lateinit var properties: SwaggerProperties

    @Bean
    fun controllerApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.apiPackage))
                .paths(PathSelectors.any())
                .build()
    }

    /**
     * describe: API信息
     * author 候帅
     * date 2018/9/17 下午3:09
     */
    private fun apiInfo(): ApiInfo {
        val contact = Contact(properties.author, properties.email, properties.web)
        return ApiInfoBuilder()
                .title(properties.title)
                .contact(contact)
                .description(properties.des)
                .version(properties.version)
                .build()
    }

}