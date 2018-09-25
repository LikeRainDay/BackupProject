package com.andy.service.servierusercenter.controller

import com.andy.service.servierusercenter.service.IGroupIconService
import io.swagger.annotations.Api
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

/**
 * describe: 组的图表字典管理
 * author 候帅
 * date 2018/9/21 下午1:55
 */
@Api(value = "GroupIconController", description = "组的图标字典")
@RestController
class GroupIconController {

    private val log: Logger = LoggerFactory.getLogger(GroupIconController::class.java)

    @Autowired
    private lateinit var iGroupIconService: IGroupIconService


    fun addGroupIcon(): Unit {


    }

}