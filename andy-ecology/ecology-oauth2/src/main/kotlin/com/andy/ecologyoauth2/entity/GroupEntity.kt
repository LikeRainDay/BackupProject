package com.andy.ecologyoauth2.entity

import org.hibernate.annotations.GenericGenerator
import javax.management.relation.Role
import javax.persistence.*
import javax.validation.constraints.NotNull


/**
 * describe: 用户组
 * author 候帅
 * date 2018/8/20 下午2:10
 */
@Entity
@Table(name = "auth_group")
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


//    @ManyToMany(mappedBy = "userGroupId", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
//    lateinit var userId: MutableSet<UserEntity>
//
//    @ManyToMany(cascade = [CascadeType.REFRESH], fetch = FetchType.LAZY)
//    @JoinTable(name = "it_groupRole",
//            joinColumns = [
//                JoinColumn(name = "group_ref_group", referencedColumnName = "id")
//            ],
//            inverseJoinColumns = [
//                JoinColumn(name = "group_ref_role", referencedColumnName = "id")
//            ])
//    lateinit var roleId: MutableSet<RoleEntity>

}