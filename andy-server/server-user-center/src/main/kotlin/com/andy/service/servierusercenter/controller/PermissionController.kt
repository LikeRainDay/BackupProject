package com.andy.service.servierusercenter.controller

import com.andy.andycommonbean.controller.BaseController
import com.andy.andycommonbean.request.PageParams
import com.andy.andycommonbean.response.BaseResponse
import com.andy.andycommonbean.response.ResultResponse
import com.andy.service.servierusercenter.service.IPermissionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * describe: 权限控制层
 * author 候帅
 * date 2018/9/2 下午10:27
 */
@Api(value = "PermissionController", description = "权限字典内容")
@RestController
@RequestMapping(value = ["/admin/permission"])
class PermissionController : BaseController() {

    private val log: Logger = LoggerFactory.getLogger(PermissionController::class.java)

    @Autowired
    private lateinit var iPermissionService: IPermissionService

    @ApiOperation(value = "创建新的权限描述")
    @ApiImplicitParams(
            ApiImplicitParam(name = "pmName", required = true, value = "权限名", dataType = "String", paramType = "query"),
            ApiImplicitParam(name = "pmType", required = true, value = "权限类型", dataType = "String", paramType = "query")
    )
    @PostMapping(value = ["/add"])
    fun onCreatePermission(@RequestParam pmName: String,
                           @RequestParam pmType: String): BaseResponse {
        return ResultResponse.success(iPermissionService.onCreatePermission(pmName, pmType))
    }

    @ApiOperation(value = "删除对应权限内容")
    @ApiImplicitParam(name = "permissionId", value = "权限ID", required = false, dataType = "String", paramType = "path")
    @GetMapping(value = ["/{permissionId}"])
    fun onDeletePermission(@PathVariable permissionId: String): BaseResponse {
        iPermissionService.deletePermission(permissionId)
        return ResultResponse.success()
    }

    @ApiOperation(value = "修改的权限描述")
    @ApiImplicitParams(
            ApiImplicitParam(name = "pmId", required = true, value = "权限ID", dataType = "String", paramType = "query"),
            ApiImplicitParam(name = "pmName", required = true, value = "权限名", dataType = "String", paramType = "query"),
            ApiImplicitParam(name = "pmType", required = true, value = "权限类型", dataType = "String", paramType = "query")
    )
    @PutMapping(value = ["/content"])
    fun onModiftyPermission(@RequestParam pmId: String,
                            @RequestParam pmName: String?,
                            @RequestParam pmType: String?): BaseResponse {
        iPermissionService.onModiftyPermission(pmId, pmName, pmType)
        return ResultResponse.success("Modification successful")
    }

    @ApiOperation(value = "权限的分页内容")
    @ApiImplicitParam(name = "pageResult", value = "分页参数", required = false, dataType = "PageParams", paramType = "body")
    @GetMapping(value = ["/page"])
    fun onPagePermission(@RequestBody pageResult: PageParams): BaseResponse {
        val page = iPermissionService.onPage(pageResult)
        return ResultResponse.success(page)
    }

}