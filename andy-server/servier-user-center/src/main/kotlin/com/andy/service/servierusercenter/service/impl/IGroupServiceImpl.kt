package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.dao.GroupDao
import com.andy.service.servierusercenter.entity.GroupEntity
import com.andy.service.servierusercenter.service.IGroupService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IGroupServiceImpl: IGroupService{

    private val log: Logger = LoggerFactory.getLogger(IGroupServiceImpl::class.java)

    @Autowired
    private lateinit var groupDao: GroupDao

    override fun addGroup(groupEntity: GroupEntity): String {

        // TODO 增加对应的组织组信息
       return groupDao.save(groupEntity).id!!
    }

    override fun deleteGroupById(groupId: String) {
        groupDao.deleteById(groupId)
    }

    override fun modiftyGroupInfo(groupEntity: GroupEntity) {
        groupDao.save(groupEntity)
    }

    override fun modiftyGroupParent(targetId: String, childId: String) {
        val parentEntity = groupDao.findByGroupId(targetId)
        parentEntity.map {
            val childEntity = groupDao.findByGroupId(childId)
            childEntity.map {



                it.parentId = targetId
                groupDao.save(it)
            }.orElseThrow {
                throw IllegalAccessException("Not fond group info")
            }
        }.orElseThrow {
            throw IllegalAccessException("Not fond group info")
        }
    }

    override fun mergeGroup(targetId: String, childId: String) {
        val parentEntity = groupDao.findByGroupId(targetId)
        parentEntity.map {



//            val childEntity = groupDao.findByGroupId(childId)
//            childEntity.map {
//                it.parentId = targetId
//                groupDao.save(it)
//            }.orElseThrow {
//                throw IllegalAccessException("Not fond group info")
//            }
        }.orElseThrow {
            throw IllegalAccessException("Not fond group info")
        }
    }


}