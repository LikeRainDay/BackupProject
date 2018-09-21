package com.andy.service.servierusercenter.controller

import com.andy.andycommonbean.controller.BaseController
import com.andy.andycommonbean.request.PageRequest
import com.andy.andycommonbean.response.BaseResponse
import com.andy.andycommonbean.response.ResultResponse
import com.andy.service.servierusercenter.service.IPmFeatureService
import com.andy.service.servierusercenter.service.IPmFileService
import com.andy.service.servierusercenter.service.IPmMenuService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * describe: 细分后的权限字典添加接口
 * author 候帅
 * date 2018/9/20 下午4:13
 */
@Api(value = "PermissionDicController", description = "权限字典")
@RestController
@RequestMapping(value = ["/admin/permission/dic"])
class PermissionDicController : BaseController() {

    private val log: Logger = LoggerFactory.getLogger(PermissionDicController::class.java)


    @Autowired
    private lateinit var iPmFileService: IPmFileService

    @Autowired
    private lateinit var iPmFeatureService: IPmFeatureService

    @Autowired
    private lateinit var iPmMenuService: IPmMenuService


    @ApiOperation(value = "增加菜单权限", notes = "<p>此接口进行对菜单字典的增加")
    @ApiImplicitParams(
            ApiImplicitParam(value = "菜单地址", name = "menuUrl", dataType = "String", paramType = "form", required = true),
            ApiImplicitParam(value = "菜单名字", name = "menuName", dataType = "String", paramType = "form", required = true),
            ApiImplicitParam(value = "菜单描述", name = "menuDes", dataType = "String", paramType = "form", required = false),
            ApiImplicitParam(value = "菜单父ID", name = "menuParentId", dataType = "String", paramType = "form", required = false)
    )
    @PostMapping(value = ["/menu"])
    fun addMenuPms(@RequestParam(required = true) menuUrl: String,
                   @RequestParam(required = true) menuName: String,
                   @RequestParam menuDes: String,
                   @RequestParam menuParentId: String): BaseResponse {
        val permissionId: String = iPmMenuService.addMenPermission(menuUrl, menuName, menuDes, menuParentId)
        return ResultResponse.success(permissionId)
    }

    @ApiOperation(value = "获取菜单权限分页")
    @ApiImplicitParam(name = "pageRequest", value = "分页设置", required = false, dataType = "PageRequest", paramType = "body")
    @GetMapping(value = ["/menu"])
    fun getMenPmsPage(@RequestBody pageRequest: PageRequest): BaseResponse {
        val page = iPmMenuService.findPageByParam(pageRequest)
        return ResultResponse.success(page)
    }


    @ApiOperation(value = "删除菜单权限")
    @ApiImplicitParam(name = "menuId", value = "菜单ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping(value = ["/menu/{menuId}"])
    fun deleteMenuPM(@PathVariable menuId: String): BaseResponse {
        iPmMenuService.deletePermission(menuId)
        return ResultResponse.success("delete success")
    }

    @ApiOperation(value = "获取文件权限分页")
    @ApiImplicitParam(name = "pageRequest", value = "分页设置", required = false, dataType = "PageRequest", paramType = "body")
    @GetMapping(value = ["/file"])
    fun getFilePmsPage(@RequestBody pageRequest: PageRequest): BaseResponse {
        val page = iPmFileService.findPageByParam(pageRequest)
        return ResultResponse.success(page)
    }

    @ApiOperation(value = "增加文件权限")
    @ApiImplicitParam(name = "fileUrl", value = "文件路径", required = true, dataType = "String", paramType = "query")
    @PostMapping(value = ["/file"])
    fun addFilePM(@RequestParam fileUrl: String): BaseResponse {
        val permission = iPmFileService.addFilePermission(fileUrl)
        return ResultResponse.success(permission)
    }

    @ApiOperation(value = "删除文件权限")
    @ApiImplicitParam(name = "fileUrl", value = "文件ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping(value = ["/file/{fileId}"])
    fun deleteFilePM(@PathVariable fileId: String): BaseResponse {
        iPmFileService.deletePermission(fileId)
        return ResultResponse.success("delete success")
    }


    @ApiOperation(value = "获取特征权限分页")
    @ApiImplicitParam(name = "pageRequest", value = "分页设置", required = false, dataType = "PageRequest", paramType = "body")
    @GetMapping(value = ["/file"])
    fun getFeaturesPmsPage(@RequestBody pageRequest: PageRequest): BaseResponse {
        val page = iPmFeatureService.findPageByParam(pageRequest)
        return ResultResponse.success(page)
    }

    @ApiOperation(value = "增加特征权限")
    @ApiImplicitParam(name = "fileUrl", value = "文件路径", required = true, dataType = "String", paramType = "query")
    @PostMapping(value = ["/file"])
    fun addFeaturesPM(@RequestParam operationCode: String,
                      @RequestParam parentId: String,
                      @RequestParam operationName: String): BaseResponse {
        val permission = iPmFeatureService.addFeaturePermission(operationCode, parentId, operationName)
        return ResultResponse.success(permission)
    }

    @ApiOperation(value = "删除特征权限")
    @ApiImplicitParam(name = "fileUrl", value = "文件ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping(value = ["/file/{fileId}"])
    fun deleteFeaturesPM(@PathVariable fileId: String): BaseResponse {
        iPmFeatureService.deletePermission(fileId)
        return ResultResponse.success("delete success")
    }

}