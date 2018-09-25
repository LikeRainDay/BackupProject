package com.andy.service.servierusercenter.controller

import com.andy.andycommonbean.controller.BaseController
import com.andy.andycommonbean.response.BaseResponse
import com.andy.andycommonbean.response.ResultResponse
import com.andy.service.servierusercenter.service.IRoleService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * describe: 角色控制层
 * author 候帅
 * date 2018/9/2 下午10:27
 */
@Api(value = "RoleController", description = "角色管理")
@RestController
@RequestMapping(value = ["/admin/role"])
class RoleController : BaseController() {

    private val log: Logger = LoggerFactory.getLogger(RoleController::class.java)

    @Autowired
    private lateinit var iRoleService: IRoleService

    @ApiOperation(value = "增加角色内容")
    @ApiImplicitParams(
            ApiImplicitParam(name = "roleName", value = "角色名", required = true, dataType = "String", paramType = "query"),
            ApiImplicitParam(name = "roleDes", value = "角色描述", required = false, dataType = "String", paramType = "query")
    )
    @PostMapping(value = ["/add"])
    fun onCreateRole(@RequestParam(required = true) roleName: String,
                     @RequestParam roleDes: String): BaseResponse {
        val onCreateRole = iRoleService.onCreateRole(roleName, roleDes)
        return ResultResponse.success(onCreateRole)

    }

    @ApiOperation(value = "通过ID进行删除")
    @ApiImplicitParam(name = "id", value = "角色ID", dataType = "String", paramType = "path", required = true)
    @DeleteMapping(value = ["/{id}"])
    fun onDeleteRoleById(@PathVariable id: String): BaseResponse {
        iRoleService.onDeleteRoleById(id)
        return ResultResponse.success()
    }

    @ApiOperation(value = "查询所有的角色信息")
    @GetMapping(value = ["/all"])
    fun onFindRoles(): BaseResponse {
        val roles = iRoleService.onFindRoles()
        return ResultResponse.success(roles)
    }

    @ApiOperation(value = "修改角色信息")
    @ApiImplicitParams(
            ApiImplicitParam(name = "id", value = "角色ID", dataType = "String", paramType = "path", required = true),
            ApiImplicitParam(name = "roleName", value = "角色名", dataType = "String", paramType = "query", required = true),
            ApiImplicitParam(name = "roleDes", value = "角色描述", dataType = "String", paramType = "query", required = true)
    )
    @PutMapping(value = ["/{id}"])
    fun onModiftyRoles(@PathVariable id: String,
                       @RequestParam roleName: String?,
                       @RequestParam roleDes: String?): BaseResponse {
        iRoleService.onModiftyRoles(id, roleDes, roleDes)
        return ResultResponse.success()
    }

}