package com.andy.server.serveremail.controller

import com.andy.andycommonbean.bean.EmailBean
import com.andy.andycommonbean.response.BaseResponse
import com.andy.andycommonbean.response.ResultResponse
import com.andy.server.serveremail.service.IEmailService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
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


    @ApiOperation(value = "发送邮件")
    @ApiImplicitParam(name = "emailEntity", value = "邮件实体", required = false, dataType = "EmailBean", paramType = "body")
    @PostMapping(value = ["/send"])
    fun sendEmail(@RequestBody emailEntity: EmailBean): EmailBean {
        return iEmailService.sendEmtail(emailEntity)
    }


    @ApiOperation(value = "确认邮件的验证码是否正确")
    @ApiImplicitParams(
            ApiImplicitParam(name = "email", value = "邮件地址", required = false, dataType = "String", paramType = "query"),
            ApiImplicitParam(name = "code", value = "验证码", required = false, dataType = "String", paramType = "query")
    )
    @PostMapping(value = ["/affirm"])
    fun validEmailCode(@RequestParam("email") email: String, @RequestParam("code") code: String): Boolean {
        return iEmailService.emailValid(code, email)
    }

    @ApiOperation(value = "发送验证码")
    @ApiImplicitParam(name = "receiver", value = "接收人信息", required = true, dataType = "string", paramType = "query")
    @PostMapping(value = ["/send/identify_code"])
    fun sendIdentifyingCode(@RequestParam(required = true) receiver: String): BaseResponse {
        iEmailService.sendIdentifyCode(receiver)
        return ResultResponse.success()
    }
    
}