package com.andy.server.serveremail.controller

import com.andy.andycommonbean.bean.EmailBean
import com.andy.server.serveremail.entity.EmailEntity
import com.andy.server.serveremail.service.IEmailService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/email")
class EmailController {

    private val log: Logger = LoggerFactory.getLogger(EmailController::class.java)

    @Autowired
    private lateinit var iEmailService: IEmailService

    /**
     * describe: 进行发送邮件
     * author 候帅
     * date 2018/8/25 下午4:53
     */
    @PostMapping("/send")
    fun sendEmail(@RequestBody emailEntity: EmailBean): EmailBean {
        return iEmailService.sendEmtail(emailEntity)
    }


}