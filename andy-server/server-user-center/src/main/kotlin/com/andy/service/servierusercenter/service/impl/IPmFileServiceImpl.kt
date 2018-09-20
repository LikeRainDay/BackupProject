package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.dao.PmFileDao
import com.andy.service.servierusercenter.entity.PmFileEntity
import com.andy.service.servierusercenter.service.IPmFileService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class IPmFileServiceImpl : IPmFileService {

    private val log: Logger = LoggerFactory.getLogger(IPmFileServiceImpl::class.java)

    @Autowired
    private lateinit var pmFileDao: PmFileDao

    override fun addPermission(permission: PmFileEntity): String {
        return pmFileDao.save(permission).id!!
    }

    override fun modiftyPermission(permission: PmFileEntity) {
        pmFileDao.save(permission)
    }

    override fun deletePermission(perId: String) {
        pmFileDao.deleteById(perId)
    }

    override fun findAll(): Optional<MutableList<PmFileEntity>> {
        return Optional.ofNullable(pmFileDao.findAll().toMutableList())
    }


}