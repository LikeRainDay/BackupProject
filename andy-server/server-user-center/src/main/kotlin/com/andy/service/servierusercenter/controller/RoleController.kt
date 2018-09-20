package com.andy.service.servierusercenter.controller

import com.andy.andycommonbean.controller.BaseController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * describe: 角色控制层
 * author 候帅
 * date 2018/9/2 下午10:27
 */
@RestController
@RequestMapping(value = ["/admin/role"])
class RoleController : BaseController() {

    private val log: Logger = LoggerFactory.getLogger(RoleController::class.java)

}