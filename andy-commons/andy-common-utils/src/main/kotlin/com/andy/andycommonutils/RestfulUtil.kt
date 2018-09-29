package com.andy.andycommonutils

import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

/**
 * describe: RestFul 的接口请求
 * author 候帅
 * date 2018/8/25 上午7:59
 */
object RestfulUtil {

    /**
     * describe: Post请求
     * author 候帅
     * date 2018/8/25 上午7:59
     * @param url 地址
     * @param params 参数
     * @param redirect 返回类型
     * @return 响应内容
     */
    fun <T, D> sendPostForEntity(url: String, params: D, redirect: Class<T>): ResponseEntity<T> {
        val r = HttpEntity(params)
        val restTemplate = RestTemplate()
        return restTemplate.postForEntity(url, r, redirect)
    }

    /**
     * describe: Post 返回字符串
     * author 候帅
     * date 2018/8/25 上午8:04
     * @param url 地址
     * @param params 参数内容
     * @return   响应内容结果
     */
    fun <D> sendPost(url: String, params: D): ResponseEntity<String> {
        val r = HttpEntity(params)
        val restTemplate = RestTemplate()
        return restTemplate.postForEntity(url, r, String::class.java)
    }

    /**
     * describe: Get 请求
     * author 候帅
     * date 2018/8/25 上午8:07
     * @param url 地址路径
     * @param redirect 返回类型
     * @return  对应返回的类型实体
     */
    fun <A : Any> sendGet(url: String, redirect: Class<A>): A? {
        val restTemplate = RestTemplate()
        return restTemplate.getForObject(url, redirect)
    }
    /**
     * describe: Get 请求
     * author 候帅
     * date 2018/8/25 上午8:07
     * @param url 地址路径
     * @param redirect 返回类型
     * @return  对应返回的类型实体
     */
    fun sendGet(url: String): String? {
        val restTemplate = RestTemplate()
        return restTemplate.getForObject(url, String::class.java)
    }




}