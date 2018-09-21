package com.andy.service.servierusercenter.service


/**
 * describe: 进行角色管理服务
 * author 候帅
 * date 2018/8/31 下午4:53
 */
interface IRoleService {


    /**
     * describe: 增加角色秒速
     * author 候帅
     * date 2018/9/21 下午3:51
     */
    fun onCreateRole(roleName: String, roleDes: String): String?

//    /**
//     * describe: 增加角色
//     * author 候帅
//     * date 2018/8/31 下午5:17
//     */
//    fun addRole(role: RoleEntity): RoleEntity
//
//    /**
//     * describe: 删除角色
//     * author 候帅
//     * date 2018/8/31 下午5:17
//     * @param roleId 角色对应的ID
//     */
//    fun removeRole(roleId: String)
//
//    /**
//     * describe: 修改角色信息
//     * author 候帅
//     * date 2018/8/31 下午5:17
//     */
//    fun changeRole(role: RoleEntity)
//
//    /**
//     * describe: 查询所有角色
//     * author 候帅
//     * date 2018/9/1 下午7:52
//     */
//    fun findAll(): Optional<MutableList<RoleEntity>>
//


}