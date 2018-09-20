package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.dao.PermissionDao
import com.andy.service.servierusercenter.entity.PermissionEntity
import com.andy.service.servierusercenter.service.IPermissionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class IPermissionServiceImpl: IPermissionService {

    private val log: Logger = LoggerFactory.getLogger(IPermissionServiceImpl::class.java)


    @Autowired
    private lateinit var permissionDao: PermissionDao

    override fun addPermission(permission: PermissionEntity): String {
        return permissionDao.save(permission).id!!
    }

    override fun modiftyPermission(permission: PermissionEntity) {
        permissionDao.save(permission)
    }

    override fun deletePermission(perId: String) {
        permissionDao.deleteById(perId)
    }

    override fun findAll(): Optional<MutableList<PermissionEntity>> {
        return Optional.ofNullable(permissionDao.findAll().toMutableList())
    }


}