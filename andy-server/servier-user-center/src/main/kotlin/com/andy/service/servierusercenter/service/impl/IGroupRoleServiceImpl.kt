package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.entity.GroupEntity
import com.andy.service.servierusercenter.entity.RoleEntity
import com.andy.service.servierusercenter.service.IRoleService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * describe: 进行组角色管理
 * author 候帅
 * date 2018/8/31 下午4:53
 */
@Service(value = "group_role_service")
class IGroupRoleServiceImpl: IRoleService<GroupEntity> {

    private val log: Logger = LoggerFactory.getLogger(IGroupRoleServiceImpl::class.java)

    override fun addRole(role: RoleEntity): RoleEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeRole(role: RoleEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeRole(role: RoleEntity): RoleEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkoutRoleByName(roleName: String): RoleEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkoutRoleByStatus(status: Int): List<RoleEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkoutRoleByRoleType(roleType: String): RoleEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}