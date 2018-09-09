package com.andy.ecologygetway.fallback


import com.andy.andycommonbean.response.ResultResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.io.InputStream

/**
 * Created by 候帅 on 2018/3/19
 *
 */
@Component
class ServiceFallbackProvider:FallbackProvider {

    /**
    * 描述:          进行回退相应
    * 创建时间:     2018/3/21 19:32
    * 创建人:       候帅
    * 邮箱:         houshuai0816@126.com
    * @param route 路由地址
    * @param cause 引起的异常
    */
    override fun fallbackResponse(route: String?, cause: Throwable?): ClientHttpResponse {
        cause!!.printStackTrace()
        return object :ClientHttpResponse{
            override fun getHeaders(): HttpHeaders {
                val httpHeaders = HttpHeaders()
                httpHeaders.contentType = MediaType.APPLICATION_JSON_UTF8
                return httpHeaders
            }

            override fun getStatusCode(): HttpStatus {
                return HttpStatus.OK

            }

            override fun getRawStatusCode(): Int {
                return statusCode.value()

            }

            override fun close() {
            }

            override fun getStatusText(): String {
                return statusCode.reasonPhrase
            }

            override fun getBody(): InputStream {
                return ByteArrayInputStream(ObjectMapper().writeValueAsString(ResultResponse.error("当前微服务不可用，请稍后尝试")).toByteArray())
            }
        }
    }


    override fun getRoute(): String {
        // 表明是为那个微服务提供回退，
        return "*" //表示全部
    }


}