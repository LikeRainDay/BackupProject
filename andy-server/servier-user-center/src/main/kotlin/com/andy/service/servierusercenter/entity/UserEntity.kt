package com.andy.service.servierusercenter.entity

import org.hibernate.annotations.ColumnDefault
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
class UserEntity: AbstractEntity<Long>() {


    @Size(max = 50)
    @NotNull
    @Column(name = "username", unique = true, nullable = false, length = 50)
    lateinit var username: String

    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    lateinit var password: String

    @NotNull
    @Column(nullable = false)
    @ColumnDefault(value = "False")
    var disabled: Boolean = false


    // 昵称
    @NotNull
    @Column(name = "nick_name", nullable = false)
    lateinit var nickname: String

    // 出生日期
    @Column(name = "data_of_birth")
    lateinit var dateOfBirth: String


    // 地址
//    lateinit var

}