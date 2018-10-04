package com.andy.service.servierusercenter.service

import com.andy.andycommonbean.request.PageParams
import com.andy.service.servierusercenter.entity.PermissionEntity
import org.springframework.data.domain.Page

/**
 * describe: 权限管理
 * author 候帅
 * date 2018/9/21 下午2:07
 */
interface IPermissionService : BasePermissionService<PermissionEntity> {

    /**
     * describe: 创建权限
     * author 候帅
     * date 2018/9/21 下午2:25
     */
    fun onCreatePermission(pmName: String, pmType: String): String?

    /**
     * describe: 修改权限描述内容
     * author 候帅
     * date 2018/9/21 下午2:25
     */
    fun onModiftyPermission(pmId: String, pmName: String?, pmType: String?)

    /**
     * describe: 分页查询内容
     * author 候帅
     * date 2018/9/21 下午2:37
     */
    fun onPage(pageResult: PageParams): Page<PermissionEntity>
}