package com.andy.server.serveremail.controller

import com.andy.andycommonbean.bean.EmailBean
import com.andy.server.serveremail.service.IEmailService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/email")
class EmailController {

    private val log: Logger = LoggerFactory.getLogger(EmailController::class.java)

    @Autowired
    private lateinit var iEmailService: IEmailService

    /**
     * describe: 进行发送邮件
     * author 候帅
     * date 2018/8/25 下午4:53
     */
    @PostMapping(value = ["/send"])
    fun sendEmail(@RequestBody emailEntity: EmailBean): EmailBean {
        return iEmailService.sendEmtail(emailEntity)
    }

    
    /**
     * describe: 确认邮件的验证码是否正确
     * author 候帅  
     * date 2018/8/28 上午11:10  
     */
    @PostMapping(value = ["/affirm"])
    fun validEmailCode(@RequestParam("mobile") email: String, @RequestParam("code") code: String): Boolean {
        return iEmailService.emailValid(code, email)
    }

}