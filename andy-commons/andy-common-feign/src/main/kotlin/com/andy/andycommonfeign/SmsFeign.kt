package com.andy.andycommonfeign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * describe: 短信的消费服务
 * author 候帅
 * date 2018/8/25 下午2:43
 */
@FeignClient(name = "service-sms")
interface SmsFeign {

    /**
     * describe: 发短信到对应 手机上
     * author 候帅
     * date 2018/8/25 下午2:46
     */
    @RequestMapping(value = ["/send"])
    fun sendMessage(@RequestParam("mobile") mobile: String): String?

    /**
     * describe: 确认短信信息是否正确
     * author 候帅
     * date 2018/8/25 下午3:38
     * @param mobile 手机号
     * @param code 验证码
     * @return   true 验证码信息正确
     */
    @RequestMapping(value = ["/affirm"])
    fun verificationCode(@RequestParam("mobile") mobile: String, @RequestParam("code") code: String): Boolean
}