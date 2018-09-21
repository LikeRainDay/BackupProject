package com.andy.service.servierusercenter.service

import com.andy.service.servierusercenter.entity.PmMenuEntity


interface IPmMenuService : BasePermissionService<PmMenuEntity> {
    /**
     * describe: 增加菜单权限
     * author 候帅
     * date 2018/9/21 上午9:37
     */
    fun addMenPermission(menuUrl: String, menuName: String, menuDes: String, menuParentId: String? = null): String
}