package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.entity.PmPageEntity
import com.andy.service.servierusercenter.service.IPermissionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service(value = "pm_page_service")
class IPmPageServiceImpl: IPermissionService<PmPageEntity> {

    private val log: Logger = LoggerFactory.getLogger(IPmPageServiceImpl::class.java)


    override fun addPermission(permission: PmPageEntity): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modiftyPermission(permission: PmPageEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletePermission(perId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): Optional<List<PmPageEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}