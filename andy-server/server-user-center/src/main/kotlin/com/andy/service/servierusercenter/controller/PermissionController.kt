package com.andy.service.servierusercenter.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * describe: 权限控制层
 * author 候帅
 * date 2018/9/2 下午10:27
 * @param
 * @return
 */
@RestController
@RequestMapping(value = ["/admin/permission"])
class PermissionController {

    private val log: Logger = LoggerFactory.getLogger(PermissionController::class.java)

}