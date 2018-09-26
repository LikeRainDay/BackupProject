package com.andy.service.servierusercenter.controller

import com.andy.andycommonbean.response.BaseResponse
import com.andy.andycommonbean.response.ResultResponse
import com.andy.service.servierusercenter.service.IGroupIconService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * describe: 组的图表字典管理
 * author 候帅
 * date 2018/9/21 下午1:55
 */
@Api(value = "GroupIconController", description = "组的图标字典")
@RestController
@RequestMapping(value = ["/admin/group/icon"])
class GroupIconController {

    private val log: Logger = LoggerFactory.getLogger(GroupIconController::class.java)

    @Autowired
    private lateinit var iGroupIconService: IGroupIconService


    @ApiOperation(value = "增加组图标")
    @ApiImplicitParam(name = "url", value = "图标地址", required = false, dataType = "String", paramType = "query")
    @PostMapping("/add")
    fun addGroupIcon(@RequestParam url: String): BaseResponse {
        val addGroupIcon = iGroupIconService.addGroupIcon(url)
        return ResultResponse.success(addGroupIcon)
    }

    @ApiOperation(value = "删除组图标")
    @ApiImplicitParam(name = "id", value = "图标ID", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = ["/{id}"])
    fun deleteGroupIcon(@PathVariable id: Long): BaseResponse {
        iGroupIconService.deleteGroupIcon(id)
        return ResultResponse.success()
    }

    @ApiOperation(value = "获取所有组图标信息")
    @GetMapping(value = ["/all"])
    fun findAllGroupIcon(): BaseResponse {
        return ResultResponse.success(iGroupIconService.findAll())
    }

    @ApiOperation(value = "修改组信息内容")
    @ApiImplicitParams(
            ApiImplicitParam(name = "id", value = "组ID内容", dataType = "String", paramType = "path", required = true),
            ApiImplicitParam(name = "url", value = "地址内容", dataType = "Long", paramType = "query", required = true)
    )
    @PutMapping(value = ["/{id}"])
    fun modiftyGroupIcon(@PathVariable id: Long, @RequestParam url: String): BaseResponse {
        iGroupIconService.modiftyGroupIcon(id, url)
        return ResultResponse.success()
    }
}