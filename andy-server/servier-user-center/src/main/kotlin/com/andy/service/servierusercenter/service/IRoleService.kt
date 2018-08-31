package com.andy.service.servierusercenter.service

import com.andy.service.servierusercenter.entity.RoleEntity

/**
 * describe: 进行角色管理服务
 * author 候帅
 * date 2018/8/31 下午4:53
 */
interface IRoleService<T> {

    /**
     * describe: 增加角色
     * author 候帅  
     * date 2018/8/31 下午5:17  
     */
    fun addRole(role: RoleEntity): RoleEntity
    
    /**
     * describe: 删除角色
     * author 候帅  
     * date 2018/8/31 下午5:17  
     */
    fun removeRole(role: RoleEntity)

    /**
     * describe: 修改角色
     * author 候帅  
     * date 2018/8/31 下午5:17  
     */
    fun changeRole(role: RoleEntity): RoleEntity
    
    /**
     * describe: 查询角色
     * author 候帅  
     * date 2018/8/31 下午5:17  
     */
    fun checkoutRoleByName(roleName: String): RoleEntity


    /**
     * describe: 查询角色通过角色状态
     * author 候帅
     * date 2018/8/31 下午5:37
     */
    fun checkoutRoleByStatus(status: Int): List<RoleEntity>


    /**
     * describe: 查询角色
     * author 候帅
     * date 2018/8/31 下午5:38
     * @param roleType 角色类型
     */
    fun checkoutRoleByRoleType(roleType: String): RoleEntity

}