package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.dao.PmPageDao
import com.andy.service.servierusercenter.entity.PmPageEntity
import com.andy.service.servierusercenter.service.IPermissionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service(value = "pm_page_service")
class IPmPageServiceImpl: IPermissionService<PmPageEntity> {

    private val log: Logger = LoggerFactory.getLogger(IPmPageServiceImpl::class.java)

    @Autowired
    private lateinit var pmPageDao: PmPageDao

    override fun addPermission(permission: PmPageEntity): String {
        return pmPageDao.save(permission).id!!
    }

    override fun modiftyPermission(permission: PmPageEntity) {
        pmPageDao.save(permission)
    }

    override fun deletePermission(perId: String) {
        pmPageDao.deleteById(perId)
    }

    override fun findAll(): Optional<MutableList<PmPageEntity>> {
        return Optional.ofNullable(pmPageDao.findAll().toMutableList())
    }


}