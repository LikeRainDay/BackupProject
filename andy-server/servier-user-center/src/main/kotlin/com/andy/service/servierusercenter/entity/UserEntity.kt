package com.andy.service.servierusercenter.entity

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
/**
 * FileName: UserDetailsEntity
 * author:   候帅
 * data:     21/08/2018 21:24
 * Description: 用户登陆表
 * History:
 */
@Entity
@Table(name = "users")
class UserEntity: AbstractEntity<Long>() {


    @Size(max = 50)
    @NotNull
    @Column(name = "username", unique = true, nullable = false, length = 50)
    lateinit var user: String

    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    lateinit var password: String

    @Size(max = 50)
    @Column(name = "email", nullable = true)
    lateinit var email: String

    // 状态信息  0: 存在  1: 禁言  3: 已删除
    @Column(name = "status")
    var status: Int = 0

    // 电话
    lateinit var phone: String

    // 用户详情信息
    @OneToOne(cascade = [CascadeType.ALL],fetch = FetchType.LAZY, mappedBy = "users")
    lateinit var userDetails: UserDetailsEntity

    // 用户组
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(name = "it_userGroup",
        joinColumns = [
            JoinColumn(name = "ref_user", referencedColumnName = "id")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "ref_group", referencedColumnName = "id")
        ])
    lateinit var group: MutableSet<GroupEntity>


    // 用户角色
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(name = "it_userRole",
            joinColumns = [
                JoinColumn(name = "ref_user", referencedColumnName = "id")
            ],
            inverseJoinColumns = [
                JoinColumn(name = "ref_role", referencedColumnName = "id")
            ])
    lateinit var role: MutableSet<RoleEntity>


    // 用户对应的第三方权限
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    lateinit var userAuths: MutableSet<UserAuthsEntity>





}