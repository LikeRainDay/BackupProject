package com.andy.service.servierusercenter.entity


import javax.persistence.*

/**
 * FileName: UserAuthsEntity
 * author:   候帅
 * data:     21/08/2018 21:46
 * Description: 用户授权表
 * History:
 */
@Entity
@Table(name = "user_auths")
class UserAuthsEntity: AbstractEntity() {

    @Column
    var identityType: Int = 0

    // 标识  （手机号  邮箱  用户名 或 第三方唯一凭证）
    lateinit var identifier: String

    //密码凭证
    lateinit var credential: String

    // 对应用户
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = [CascadeType.DETACH, CascadeType.REFRESH])
    @JoinColumn(name = "user_id", nullable = false)
    lateinit var user: UserEntity
}