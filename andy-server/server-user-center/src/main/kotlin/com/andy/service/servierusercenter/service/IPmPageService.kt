package com.andy.service.servierusercenter.service

import com.andy.andycommonbean.request.PageRequest
import com.andy.service.servierusercenter.entity.PmPageEntity
import org.springframework.data.domain.Page


interface IPmPageService : BasePermissionService<PmPageEntity> {

    fun findPageByParam(pageRequest: PageRequest): Page<PmPageEntity>

    fun addPagePermission(pageCoding: String, pageName: String, pageDes: String): String?
}