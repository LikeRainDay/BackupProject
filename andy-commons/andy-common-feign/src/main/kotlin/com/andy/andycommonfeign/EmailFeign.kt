package com.andy.andycommonfeign

import com.andy.andycommonbean.bean.EmailBean
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "service-email")
@RequestMapping("/api/email")
interface EmailFeign {

    /**
     * describe: 发送邮件
     * author 候帅
     * date 2018/8/25 下午2:46
     */
    @RequestMapping(value = ["/send"])
    fun sendMessage(@RequestBody email: EmailBean): EmailBean


    /**
     * describe: 确认邮件验证码 是否正确
     * author 候帅
     * date 2018/8/25 下午3:38
     * @param mobile 手机号
     * @param code 验证码
     * @return   true 验证码信息正确
     */
    @RequestMapping(value = ["/affirm"], method = [RequestMethod.POST])
    fun validEmailCode(@RequestParam("mobile") email: String, @RequestParam("code") code: String): Boolean
}