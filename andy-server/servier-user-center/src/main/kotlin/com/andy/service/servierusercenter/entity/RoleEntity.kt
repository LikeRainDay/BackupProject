package com.andy.service.servierusercenter.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "role")
class RoleEntity: AbstractEntity<Long>() {


    // 角色名
    @NotNull
    @Column(name = "role_name", nullable = false)
    lateinit var roleName: String

    // 角色属性  eg: ROlE_ADMIN  等
    @Column(name = "role_type", nullable = false, unique = true)
    lateinit var roleType: String

    // 当前角色 状态 0: 代表角色存在  1 : 角色禁用  3: 代表角色 删除
    @Column
    var status: Int = 0

    // 角色和组表
    @ManyToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = [CascadeType.REFRESH, CascadeType.DETACH])
    lateinit var group: Set<GroupEntity>

    // 角色和用户表
    @ManyToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = [CascadeType.REFRESH, CascadeType.DETACH])
    lateinit var user: Set<UserEntity>

    // 角色和权限表
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(name = "it_RP",
            joinColumns = [
                JoinColumn(name = "FK_RP_REF_ROLE", referencedColumnName = "id")
            ],
            inverseJoinColumns = [
                JoinColumn(name = "FK_RP_REF_PERMISSION", referencedColumnName = "id")
            ])
    lateinit var permission: Set<PermissionEntity>


}