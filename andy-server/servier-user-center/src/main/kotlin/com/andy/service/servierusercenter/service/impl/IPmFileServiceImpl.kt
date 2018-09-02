package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.entity.PmFileEntity
import com.andy.service.servierusercenter.service.IPermissionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service(value = "pm_file_service")
class IPmFileServiceImpl: IPermissionService<PmFileEntity> {

    private val log: Logger = LoggerFactory.getLogger(IPmFileServiceImpl::class.java)


    override fun addPermission(permission: PmFileEntity): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modiftyPermission(permission: PmFileEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletePermission(perId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): Optional<MutableList<PmFileEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}