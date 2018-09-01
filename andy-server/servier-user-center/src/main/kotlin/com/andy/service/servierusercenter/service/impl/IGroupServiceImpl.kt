package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.entity.GroupEntity
import com.andy.service.servierusercenter.service.IGroupService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class IGroupServiceImpl: IGroupService{

    private val log: Logger = LoggerFactory.getLogger(IGroupServiceImpl::class.java)


    override fun addGroup(groupEntity: GroupEntity): GroupEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteGroupById(groupId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modiftyGroupInfo(groupEntity: GroupEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modiftyGroupParent(targetId: String, childId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mergeGroup(targetId: String, childId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}