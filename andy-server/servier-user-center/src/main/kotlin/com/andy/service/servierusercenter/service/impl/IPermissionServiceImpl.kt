package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.entity.PermissionEntity
import com.andy.service.servierusercenter.service.IPermissionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service(value = "permission_service")
class IPermissionServiceImpl: IPermissionService<PermissionEntity> {

    private val log: Logger = LoggerFactory.getLogger(IPermissionServiceImpl::class.java)


    override fun addPermission(permission: PermissionEntity): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modiftyPermission(permission: PermissionEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletePermission(perId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): Optional<MutableList<PermissionEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}