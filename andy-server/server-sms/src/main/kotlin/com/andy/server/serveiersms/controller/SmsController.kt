package com.andy.server.serveiersms.controller

import com.andy.server.serveiersms.service.ISmsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/sms")
class SmsController {

    private val log: Logger = LoggerFactory.getLogger(SmsController::class.java)

    @Autowired
    private lateinit var smsService: ISmsService

    /**
     * describe: 发送短信
     * author 候帅  
     * date 2018/8/25 下午3:33  
     * @param mobile 手机号
     * @return 返回结果  
     */  
    @GetMapping(value = ["/send"])
    fun sendMessage(mobile: String): String {
        return smsService.sendMessage(mobile)!!
    }
    
    /**
     * describe: 判断是否当前短信是否相同
     * author 候帅  
     * date 2018/8/25 下午3:34  
     * @param mobile 手机号
     * @param code 验证码
     * @return true 成功 false 不成功
     */
    @GetMapping(value = ["/affirm"])
    fun verificationCode(mobile: String, code: String): Boolean {
        return smsService.identiftyCodeSuccess(mobile, code)
    }
    
}