package com.andy.service.servierusercenter.controller

import com.andy.andycommonbean.controller.BaseController
import com.andy.andycommonbean.request.PageRequest
import com.andy.andycommonbean.response.BaseResponse
import com.andy.andycommonbean.response.ResultResponse
import com.andy.service.servierusercenter.service.IPmFeatureService
import com.andy.service.servierusercenter.service.IPmFileService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * describe: 细分后的权限字典添加接口
 * author 候帅
 * date 2018/9/20 下午4:13
 * @param
 * @return
 */
@RestController
@RequestMapping(value = ["/admin/permission/dic"])
class PermissionDicController : BaseController() {

    private val log: Logger = LoggerFactory.getLogger(PermissionDicController::class.java)


    @Autowired
    private lateinit var iPmFileService: IPmFileService

    @Autowired
    private lateinit var iPmFeatureService: IPmFeatureService

    
    
    /**
     * describe: 增加菜单权限字典
     * author 候帅
     * data 20/09/2018 21:01
     * @param
     * @return
     */
    fun addMenuPms(): Unit {
        
    }
    

    /**
     * describe: 查询文件权限
     * author 候帅
     * date 2018/9/20 下午4:45
     * @param
     * @return
     */
    @GetMapping(value = ["/file"])
    fun getFilePms(@RequestBody pageRequest: PageRequest): BaseResponse {
        val page = iPmFileService.findPageByParam(pageRequest)
        return ResultResponse.success(page)
    }

    /**
     * describe: 增加文件权限
     * author 候帅
     * date 2018/9/20 下午4:02
     * @param
     * @return
     */
    @PostMapping(value = ["/file"])
    fun addFilePM(@RequestParam fileUrl: String): BaseResponse {
        val permission = iPmFileService.addFilePermission(fileUrl)
        return ResultResponse.success(permission)
    }

    /**
     * describe: 删除文件权限
     * author 候帅
     * date 2018/9/20 下午4:41
     * @param
     * @return
     */
    @DeleteMapping(value = ["/file/{fileId}"])
    fun deleteFilePM(@PathVariable fileId: String): BaseResponse {
        iPmFileService.deletePermission(fileId)
        return ResultResponse.success("delete success")
    }


}