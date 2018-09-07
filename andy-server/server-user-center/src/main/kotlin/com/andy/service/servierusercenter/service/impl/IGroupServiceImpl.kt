package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.dao.GroupDao
import com.andy.service.servierusercenter.entity.GroupEntity
import com.andy.service.servierusercenter.service.IGroupService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils
import java.util.*

@Service
class IGroupServiceImpl : IGroupService {


    private val log: Logger = LoggerFactory.getLogger(IGroupServiceImpl::class.java)

    @Autowired
    private lateinit var groupDao: GroupDao

    override fun addGroup(groupEntity: GroupEntity): String {
        val parentId = groupEntity.parentId
        val count = groupDao.count() + 1
        if (StringUtils.isEmpty(parentId)) {
            groupEntity.organizeIndex = "$count-"
            groupEntity.organizeLevel = 1
        } else {
            val parentEntity = groupDao.findByGroupId(parentId)
            parentEntity.map {
                groupEntity.organizeLevel = it.organizeLevel + 1
                groupEntity.organizeIndex = "${it.organizeIndex}$count-"
            }.orElseThrow {
                throw IllegalAccessException("not find $parentId")
            }
        }
        return groupDao.save(groupEntity).id!!
    }

    @Transactional
    override fun deleteGroupById(groupId: String) {
        val groupEntity = groupDao.findByGroupId(groupId)
        // 移除其下所有匹配的组
        groupEntity.map {
            groupDao.deleteByOrganizeIndexIsStartingWith(it.organizeIndex)
        }.orElseThrow {
            throw IllegalAccessException("not find $groupId")
        }
    }

    override fun modiftyGroupInfo(groupEntity: GroupEntity) {
        val parentId = groupEntity.parentId
        val group = groupDao.findByGroupId(groupEntity.groupId)
        group.map {
            if (it.parentId != parentId)
                throw RuntimeException("Do not operate to modify superior group information")
            it.groupDesc = groupEntity.groupDesc
            it.groupName = groupEntity.groupDesc
            groupDao.save(it)
        }.orElseThrow {
            throw IllegalAccessException("not find ${groupEntity.groupId}")
        }
    }

    override fun modiftyGroupParent(targetId: String, childId: String) {
        val parentEntity = groupDao.findByGroupId(targetId)
        parentEntity.map {
            val parentId = it.groupId
            val childEntity = groupDao.findByGroupId(childId)
            childEntity.map {
                val childIndex = it.organizeIndex
                val split = childIndex.split("-")
                var preIndex: String? = null
                for (i in 0 until split.size - 1) {
                    preIndex += "${split[i]}-"
                }
                val preData = groupDao.findByOrganizeIndexsLike(it.organizeIndex)
                preData.ifPresent {
                    it.forEach {
                        // 进行替换当前的父信息
                        val replace = it.organizeIndex.replace(childIndex, preIndex!!)
                        it.organizeLevel = replace.split("-").size.toLong()
                        groupDao.save(it)
                    }
                }
                it.parentId = parentId
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
            val organizeIndex = it.organizeIndex
            val childEntity = groupDao.findByGroupId(childId)
            childEntity.map {
                val childIndex = it.organizeIndex
                val parentId = it.parentId
                val preData = groupDao.findByOrganizeIndexsLike(it.organizeIndex)
                preData.ifPresent {
                    it.forEach {
                        // 进行替换当前的父信息
                        val replace = it.organizeIndex.replace(childIndex, organizeIndex!!)
                        it.organizeLevel = replace.split("-").size.toLong()
                        if (it.parentId == parentId)
                            it.parentId = targetId
                        groupDao.save(it)
                    }
                }
                groupDao.deleteById(childId)
            }.orElseThrow {
                throw IllegalAccessException("Not fond group info")
            }
        }.orElseThrow {
            throw IllegalAccessException("Not fond group info")
        }
    }

    override fun findGroupInfoById(id: String): Optional<GroupEntity> {
        return groupDao.findById(id)
    }

    override fun findGroupInfoByGroupId(groupId: String): Optional<GroupEntity> {
        return groupDao.findByGroupId(groupId)
    }
}