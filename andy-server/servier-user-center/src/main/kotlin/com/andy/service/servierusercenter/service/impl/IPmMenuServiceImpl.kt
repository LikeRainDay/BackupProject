package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.entity.PmMenuEntity
import com.andy.service.servierusercenter.service.IPermissionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service(value = "pm_menu_service")
class IPmMenuServiceImpl: IPermissionService<PmMenuEntity> {

    private val log: Logger = LoggerFactory.getLogger(IPmMenuServiceImpl::class.java)


    override fun addPermission(permission: PmMenuEntity): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modiftyPermission(permission: PmMenuEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletePermission(perId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): Optional<List<PmMenuEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}