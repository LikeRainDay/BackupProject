package com.andy.andycommonfeign

import com.andy.andycommonbean.bean.EmailBean
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@FeignClient(name = "service-email", path = "/email")
@Component
interface EmailFeign {

    /**
     * describe: 发送邮件
     * author 候帅
     * date 2018/8/25 下午2:46
     */
    @RequestMapping(value = ["/send"])
    fun sendMessage(@RequestBody email: EmailBean): EmailBean

}