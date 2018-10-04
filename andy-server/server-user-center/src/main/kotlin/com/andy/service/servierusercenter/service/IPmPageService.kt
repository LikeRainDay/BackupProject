package com.andy.service.servierusercenter.service

import com.andy.andycommonbean.request.PageParams
import com.andy.service.servierusercenter.entity.PmPageEntity
import org.springframework.data.domain.Page


interface IPmPageService : BasePermissionService<PmPageEntity> {

    fun findPageByParam(pageRequest: PageParams): Page<PmPageEntity>

    fun addPagePermission(pageCoding: String, pageName: String, pageDes: String): String?
}