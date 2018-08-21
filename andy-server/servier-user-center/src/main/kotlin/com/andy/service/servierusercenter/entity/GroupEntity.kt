package com.andy.service.servierusercenter.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "groups")
class GroupEntity: AbstractEntity<Long>() {

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

    // 用户信息多对多
    @ManyToMany(mappedBy = "group", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    lateinit var user: Set<UserEntity>

}