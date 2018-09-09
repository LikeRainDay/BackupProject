package com.andy.ecologyoauth2.endpoint

import com.andy.andycommonbean.response.BaseResponse
import com.andy.andycommonbean.response.ResultResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices
import org.springframework.web.bind.annotation.DeleteMapping

@FrameworkEndpoint
class TokenEndpoint {

    private val log: Logger = LoggerFactory.getLogger(TokenEndpoint::class.java)

    @Autowired
    private lateinit var consumerTokenServices: ConsumerTokenServices


    @DeleteMapping(value = ["/oauth/token"])
    fun revokeToken(access_token: String): BaseResponse {
        return if (consumerTokenServices.revokeToken(access_token))
            ResultResponse.success("注销成功")
        else
            ResultResponse.error()
    }

}