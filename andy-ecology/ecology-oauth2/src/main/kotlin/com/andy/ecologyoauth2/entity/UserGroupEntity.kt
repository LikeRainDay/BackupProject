package com.andy.ecologyoauth2.entity

import javax.persistence.*
import javax.validation.constraints.NotNull


/**
 * describe: 用户组
 * author 候帅
 * date 2018/8/20 下午2:10
 */
@Entity
@Table(name = "user_group")
class UserGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    // 用户组名
    @NotNull
    @Column(name = "group_name", nullable = false)
    lateinit var groupName: String

    // 用户组ID  随机加密内容
    @NotNull
    @Column(name = "group_id", nullable = false)
    lateinit var groupId: String

    // 当前组的 父ID
    @NotNull
    @Column(name = "parent_id", nullable = false)
    lateinit var parentId: String


    // 用户组 描述
    @Column(name = "group_desc")
    lateinit var groupDesc: String


    // TODO 中间表映射关系

}