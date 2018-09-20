package com.andy.service.servierusercenter.service

import com.andy.service.servierusercenter.entity.PmFileEntity


interface IPmFileService : BasePermissionService<PmFileEntity> {

    /**
     * describe: 向文件权限字典中增加权限地址
     * author 候帅
     * date 2018/9/20 下午4:25
     */
    fun addFilePermission(fileUrl: String): String?
}