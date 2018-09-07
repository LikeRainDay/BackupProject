package com.andy.service.servierusercenter.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "roles")
class RoleEntity: AbstractEntity() {


    // 角色名  eg: ROlE_ADMIN  等
    @NotNull
    @Column(name = "role_name", nullable = false, unique = true)
    lateinit var roleName: String

    // 角色描述
    @Column(name = "role_des", nullable = false)
    lateinit var roleDes: String

    // 当前角色 状态 0: 代表角色存在  1 : 角色禁用  3: 代表角色 删除
    @Column
    var status: Int = 0

    // 角色和组表
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = [CascadeType.REFRESH, CascadeType.DETACH])
    lateinit var group: Set<GroupEntity>

    // 角色和用户表
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = [CascadeType.REFRESH, CascadeType.DETACH])
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