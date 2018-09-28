package com.andy.service.servierusercenter.service.impl

import com.andy.andycommonutils.RandomUtil
import com.andy.service.servierusercenter.bean.GroupBean
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
import java.util.stream.Collectors

@Service
class IGroupServiceImpl : IGroupService {


    private val log: Logger = LoggerFactory.getLogger(IGroupServiceImpl::class.java)

    @Autowired
    private lateinit var groupDao: GroupDao

    override fun addGroup(groupEntity: GroupEntity): String {
        val parentId = groupEntity.parentId
        if (StringUtils.isEmpty(parentId)) {
            groupEntity.organizeLevel = 1L
            groupEntity.organizeIndex = ""
            groupEntity.groupId = RandomUtil.generteRandomUUID()
        } else {
            val parentEntity = groupDao.findByGroupId(parentId)
            parentEntity.map {
                groupEntity.organizeLevel = it.organizeLevel + 1L
                groupEntity.organizeIndex = "${it.organizeIndex}${it.id}-"
                groupEntity.groupId = RandomUtil.generteRandomUUID()
                it.isNode = true
                it.isLeaf = false
            }.orElseThrow {
                throw IllegalAccessException("not find $parentId")
            }
        }
        return groupDao.save(groupEntity).groupId
    }

    @Transactional
    override fun deleteGroupById(groupId: String) {
        val groupEntity = groupDao.findByGroupId(groupId)
        // 移除其下所有匹配的组
        groupEntity.map {
            groupDao.deleteByOrganizeIndexIsStartingWith(it.organizeIndex + it.id)
            groupDao.deleteById(it.id!!)
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
                groupDao.deleteById(it.id!!)
            }.orElseThrow {
                throw IllegalAccessException("Not fond group info")
            }
        }.orElseThrow {
            throw IllegalAccessException("Not fond group info")
        }
    }

    override fun findGroupInfoById(id: String): Optional<GroupEntity> {
        return groupDao.findByGroupId(id)
    }

    override fun findGroupInfoByGroupId(groupId: String): Optional<GroupEntity> {
        return groupDao.findByGroupId(groupId)
    }

    override fun findAllChildrenByParnetId(parentId: String): Optional<MutableList<GroupBean>> {
        val groupEntity = groupDao.findByGroupId(parentId)
        return groupEntity.map {
            val data = groupDao.findByOrganizeIndexsLike(it.organizeIndex + it.id)
            return@map childTreeNodes(data, it.groupId)
        }.orElseThrow {
            throw IllegalAccessException("Not fond group info")
        }
    }


    override fun findChildrenByParnetId(parentId: String): Optional<MutableList<GroupBean>> {
        val groupEntity = groupDao.findByGroupId(parentId)
        return groupEntity.map {
            val findByOrganizeIndex = groupDao.findByOrganizeIndex(it.organizeIndex + it.id + "-")
            return@map treeNodes(findByOrganizeIndex, it.groupId)
        }.orElseThrow { throw IllegalAccessException("Not fond group info") }
    }

    override fun findBaseGroup(): Optional<MutableList<GroupBean>> {
        return groupDao.findByOrganizeLevel(1).map {
            val collect = it.stream().map {
                val groupBean = GroupBean()
                groupBean.groupId = it.groupId
                groupBean.groupIcon = it.icon?.url
                groupBean.groupLevel = it.organizeLevel
                groupBean.groupName = it.groupName
                groupBean.isLeaf = it.isLeaf
                groupBean.isNode = it.isNode
                return@map groupBean
            }.collect(Collectors.toList())
            return@map Optional.ofNullable(collect)
        }.orElseThrow {
            throw IllegalAccessException("Not fond group info")
        }
    }


    /**
     * describe: 父节点下的一级节点信息
     * author 候帅
     * date 2018/9/20 上午9:46
     */
    fun treeNodes(data: Optional<MutableList<GroupEntity>>, parentId: String): Optional<MutableList<GroupBean>> {
        return data.map {
            it.stream().filter {
                it.parentId == parentId
            }.map {
                val groupBean = GroupBean()
                groupBean.groupId = it.groupId
                groupBean.groupIcon = it.icon?.url
                groupBean.groupLevel = it.organizeLevel
                groupBean.groupName = it.groupName
                groupBean.isLeaf = it.isLeaf
                groupBean.isNode = it.isNode
                return@map groupBean
            }.collect(Collectors.toList())
        }
    }

    /**
     * describe: 父节点下的所有节点信息
     * author 候帅
     * date 2018/9/19 下午5:40
     * @param data 待排列medate
     * @param parentId 父ID
     * @return 返回父级以下的树
     */
    fun childTreeNodes(data: Optional<MutableList<GroupEntity>>, parentId: String): Optional<MutableList<GroupBean>> {
        return data.map {
            it.stream().filter {
                it.parentId == parentId
            }.map {
                val groupBean = GroupBean()
                groupBean.groupId = it.groupId
                groupBean.groupIcon = it.icon?.url
                groupBean.groupLevel = it.organizeLevel
                groupBean.groupName = it.groupName
                groupBean.isLeaf = it.isLeaf
                groupBean.isNode = it.isNode
                val treeNodes = treeNodes(data, it.groupId)
                treeNodes.ifPresent {
                    groupBean.children = it
                }
                return@map groupBean
            }.collect(Collectors.toList())
        }
    }


}