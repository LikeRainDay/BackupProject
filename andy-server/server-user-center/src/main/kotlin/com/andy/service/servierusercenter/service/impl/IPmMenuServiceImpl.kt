package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.dao.PmMenuDao
import com.andy.service.servierusercenter.entity.PmMenuEntity
import com.andy.service.servierusercenter.service.IPmMenuService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class IPmMenuServiceImpl : IPmMenuService {

    private val log: Logger = LoggerFactory.getLogger(IPmMenuServiceImpl::class.java)

    @Autowired
    private lateinit var pmMenuDao: PmMenuDao

    override fun addPermission(permission: PmMenuEntity): String {
        return pmMenuDao.save(permission).id!!
    }

    override fun modiftyPermission(permission: PmMenuEntity) {
        pmMenuDao.save(permission)
    }

    override fun deletePermission(perId: String) {
        pmMenuDao.deleteById(perId)
    }

    override fun findAll(): Optional<MutableList<PmMenuEntity>> {
        return Optional.ofNullable(pmMenuDao.findAll().toMutableList())
    }


}