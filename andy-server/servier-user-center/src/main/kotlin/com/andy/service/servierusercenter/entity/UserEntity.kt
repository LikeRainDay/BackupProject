package com.andy.service.servierusercenter.entity

import com.andy.andycommonutils.RegexUtil
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
/**
 * FileName: UserDetailsEntity
 * author:   候帅
 * data:     21/08/2018 21:24
 * Description: 用户登陆表
 * History:
 */
@Entity
@Table(name = "users",
        indexes = [
            Index(name = "index_username", columnList = "account", unique = true),
            Index(name = "index_email", columnList = "email", unique = true),
            Index(name = "index_tel", columnList = "tel", unique = true)
        ])
class UserEntity: AbstractEntity() {


    @Size(max = 50)
    @NotNull
    @Pattern(regexp = RegexUtil.ACCOUNT_REGEX)
    @Column(name = "account", unique = true, nullable = false, length = 50)
    lateinit var account: String

    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    lateinit var password: String

    @Size(max = 50)
    @Column(name = "email", nullable = true, length = 30)
    lateinit var email: String

    // 状态信息  0: 存在  1: 禁言  3: 已删除
    @Column(name = "status")
    var status: Int = 0

    // 电话
    @Size(max = 12)
    @Column(name = "tel", nullable = true, length = 12)
    var tel: Int = 0

    // 用户详情信息
    @OneToOne(cascade = [CascadeType.ALL],fetch = FetchType.LAZY)
    @JoinColumn(name = "user_details_id", referencedColumnName = "id", unique = true)
    lateinit var userDetails: UserDetailsEntity

    // 用户组
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(name = "it_UG",
        joinColumns = [
            JoinColumn(name = "FK_GU_REF_USER", referencedColumnName = "id")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "FK_GU_REF_GROUP", referencedColumnName = "id")
        ])
    lateinit var group: MutableSet<GroupEntity>


    // 用户角色
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(name = "it_UR",
            joinColumns = [
                JoinColumn(name = "FK_UR_REF_USER", referencedColumnName = "id")
            ],
            inverseJoinColumns = [
                JoinColumn(name = "FK_UR_REF_ROLE", referencedColumnName = "id")
            ])
    lateinit var role: MutableSet<RoleEntity>

    // 用户对应的第三方权限
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    lateinit var userAuths: MutableSet<UserAuthsEntity>

    // 登录记录
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    lateinit var userLoginHistoryEntity: Set<UserLoginHistoryEntity>
}