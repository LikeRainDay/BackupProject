package com.andy.service.servierusercenter.controller

import com.andy.andycommonbean.response.BaseResponse
import com.andy.service.servierusercenter.service.IPmFeatureService
import com.andy.service.servierusercenter.service.IPmFileService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * describe: 细分后的权限字典添加接口
 * author 候帅
 * date 2018/9/20 下午4:13
 * @param
 * @return
 */
@RestController
@RequestMapping(value = ["/admin/permission/dic"])
class PermissionDicController {

    private val log: Logger = LoggerFactory.getLogger(PermissionDicController::class.java)


    @Autowired
    private lateinit var iPmFileService: IPmFileService

    @Autowired
    private lateinit var iPmFeatureService: IPmFeatureService


    /**
     * describe: 增加文件权限
     * author 候帅
     * date 2018/9/20 下午4:02
     * @param
     * @return
     */
//    @PostMapping("/add/file")
//    fun addFilePM(): BaseResponse {
//
//    }
}