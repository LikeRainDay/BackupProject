package com.andy.service.servierusercenter.service

import org.springframework.data.domain.Persistable
import java.util.*

/**
 * describe: 权限的管理 ( 需要进行多种权限的分别把控 )
 * author 候帅
 * date 2018/9/1 上午12:23
 * @param T 当前待操作的实体类型
 */
interface BasePermissionService<T : Persistable<String>> {


    /**
     * describe: 新增权限
     * author 候帅
     * date 2018/9/1 上午12:23
     * @param permission 增加新权限
     * @return 返回增加权限后端的ID
     */
    fun addPermission(permission: T): String

    /**
     * describe: 修改权限
     * author 候帅
     * date 2018/9/1 上午12:24
     * @param permission 需要修改的信息内容
     */
    fun modiftyPermission(permission: T)

    /**
     * describe: 删除权限
     * author 候帅
     * date 2018/9/1 上午12:24
     * @param perId 权限ID
     */
    fun deletePermission(perId: String)


    /**
     * describe: 查询对应权限内容
     * author 候帅
     * date 2018/9/1 下午6:34
     * @return 当前所有权限内容
     */
    fun findAll(): Optional<MutableList<T>>


}