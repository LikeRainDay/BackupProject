package com.andy.service.servierusercenter.controller

import com.andy.andycommonbean.controller.BaseController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * describe: 第三方授权管理
 * author 候帅
 * date 2018/9/2 下午10:27
 */
@RestController
@RequestMapping(value = ["/admin/auth"])
class AuthController : BaseController() {

    private val log: Logger = LoggerFactory.getLogger(AuthController::class.java)

    /**
     * describe: 第三方登录相关内容
     * author 候帅
     * date 2018/9/5 下午10:05
     * @param
     * @return
     */


}