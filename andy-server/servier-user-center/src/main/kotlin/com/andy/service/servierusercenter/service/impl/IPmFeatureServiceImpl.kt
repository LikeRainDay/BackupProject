package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.entity.PmFeaturesEntity
import com.andy.service.servierusercenter.service.IPermissionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service(value = "pm_feature_service")
class IPmFeatureServiceImpl: IPermissionService<PmFeaturesEntity> {

    private val log: Logger = LoggerFactory.getLogger(IPmFeatureServiceImpl::class.java)


    override fun addPermission(permission: PmFeaturesEntity): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modiftyPermission(permission: PmFeaturesEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletePermission(perId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): Optional<MutableList<PmFeaturesEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}