package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.dao.RoleDao
import com.andy.service.servierusercenter.entity.RoleEntity
import com.andy.service.servierusercenter.service.IRoleService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.*


/**
 * describe: 用户角色管理
 * author 候帅
 * date 2018/8/31 下午5:23
 */
@Service
class IRoleServiceImpl: IRoleService {

    private val log: Logger = LoggerFactory.getLogger(IRoleServiceImpl::class.java)


    @Autowired
    private lateinit var roleDao: RoleDao

    /**
     * describe: 增加角色 需要确定 status = 0 和 roleName 以及 roleType 不重复，才可以进行增加。
     * author 候帅
     * date 2018/9/1 下午7:39
     */
    override fun addRole(role: RoleEntity): RoleEntity {
        if (StringUtils.isEmpty(role.roleName)
                || StringUtils.isEmpty(role.roleName))
            throw IllegalArgumentException("please push your role`s name and role`s type")

        val roleEntity = roleDao.findDistinctFirstByStatusAndRoleName(0, role.roleName)
        return roleEntity
                .map {
                    return@map it
                }
                .orElseGet {
                    return@orElseGet roleDao.save(role)
                }
    }

    override fun removeRole(roleId: String) {
        roleDao.deleteById(roleId)
    }

    override fun changeRole(role: RoleEntity) {
        roleDao.save(role)
    }

    override fun findAll(): Optional<MutableList<RoleEntity>> {
        return Optional.ofNullable(roleDao.findAll().toMutableList())
    }


}