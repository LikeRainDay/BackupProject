package com.andy.service.servierusercenter.service

import com.andy.andycommonbean.request.PageParams
import com.andy.service.servierusercenter.entity.PmMenuEntity
import org.springframework.data.domain.Page


interface IPmMenuService : BasePermissionService<PmMenuEntity> {
    /**
     * describe: 增加菜单权限
     * author 候帅
     * date 2018/9/21 上午9:37
     */
    fun addMenPermission(menuUrl: String, menuName: String, menuDes: String, menuParentId: String? = null): String

    /**
     * describe: 进行分页查询菜单字典内容
     * author 候帅
     * date 2018/9/21 上午10:06
     */
    fun findPageByParam(pageRequest: PageParams): Page<PmMenuEntity>
}