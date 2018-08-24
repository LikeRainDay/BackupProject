package com.andy.service.servierusercenter.entity

import com.andy.corejpa.AbstractIdAuditable
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * describe: 用户登录历史表
 * author 候帅
 * date 2018/8/23 上午9:45
 */
@Entity
@Table(name = "user_login_history")
class UserLoginHistoryEntity: AbstractIdAuditable() {

    // 登录的账号
    @Size(max = 50)
    @NotNull
    @Column(name = "account", nullable = false, length = 50)
    lateinit var username: String

    // 登录的IP
    lateinit var ip: String

    // 经度
    @Size(max = 20)
    @NotNull
    @Column(name = "account", length = 20)
    lateinit var longitube: String

    // 纬度
    @Size(max = 20)
    @NotNull
    @Column(name = "account", length = 20)
    lateinit var latitude: String

    // 登录状态 0: 登录成功  1: 登录失败
    var loginStatus: Int = 0

    // 失败登录的密码 ( 用来进行做处理记录 )
    @Size(min = 6, max = 20)
    @NotNull
    @Column(name = "account", length = 20)
    lateinit var loginFailPass: String


    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = [CascadeType.DETACH, CascadeType.REFRESH])
    @JoinColumn(name = "user_id")
    lateinit var user: UserEntity
}