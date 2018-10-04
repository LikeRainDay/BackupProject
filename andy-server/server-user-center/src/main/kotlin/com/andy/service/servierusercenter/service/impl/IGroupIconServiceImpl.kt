package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.dao.GroupIconDao
import com.andy.service.servierusercenter.entity.GroupIconEntity
import com.andy.service.servierusercenter.service.IGroupIconService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * FileName: IGroupIconServiceImpl
 * author:   候帅
 * data:     25/09/2018 23:00
 * Description: 组图标管理
 * History:
 */
@Service
class IGroupIconServiceImpl : IGroupIconService {


    @Autowired
    private lateinit var groupIconDao: GroupIconDao

    override fun addGroupIcon(url: String): Long {
        val groupIconEntity = GroupIconEntity()
        groupIconEntity.url = url
        return groupIconDao.save(groupIconEntity).id!!
    }

    override fun deleteGroupIcon(id: Long) {
        groupIconDao.deleteById(id)
    }

    override fun findAll(): List<GroupIconEntity> {
        return groupIconDao.findAll()
    }

    override fun modiftyGroupIcon(id: Long, url: String) {
        val groupIcon = groupIconDao.findById(id)
        groupIcon.map {
            it.url = url
            groupIconDao.save(it)
        }.orElseThrow {
            throw IllegalAccessException("not found $id")
        }

    }

}