package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.dao.PmFeaturesDao
import com.andy.service.servierusercenter.entity.PmFeaturesEntity
import com.andy.service.servierusercenter.service.IPmFeatureService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class IPmFeatureServiceImpl : IPmFeatureService {

    private val log: Logger = LoggerFactory.getLogger(IPmFeatureServiceImpl::class.java)

    @Autowired
    private lateinit var pmFeaturesDao: PmFeaturesDao

    override fun addPermission(permission: PmFeaturesEntity): String {
        return pmFeaturesDao.save(permission).id!!
    }

    override fun modiftyPermission(permission: PmFeaturesEntity) {
        pmFeaturesDao.save(permission)
    }

    override fun deletePermission(perId: String) {
        pmFeaturesDao.deleteById(perId)
    }

    override fun findAll(): Optional<MutableList<PmFeaturesEntity>> {
        return Optional.ofNullable(pmFeaturesDao.findAll().toMutableList())
    }

}