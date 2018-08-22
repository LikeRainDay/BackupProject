package com.andy.service.servierusercenter.entity

import javax.persistence.*
import javax.validation.constraints.Size

/**
 * FileName: UserDetailsEntity
 * author:   候帅
 * data:     21/08/2018 21:24
 * Description: 用户详情信息
 * History:
 */
@Entity
@Table(name = "user_details")
class UserDetailsEntity: AbstractEntity() {


    // 用户性别
    var sex: Int = 0

    // 用户地址信息
    lateinit var adress: String

    // 状态信息
    var status: Int = 0

    // 头像 地址
    @Column(name = "avator")
    lateinit var avator: String

    // 邮编
    @Size(max = 30)
    @Column(name = "zip_code", nullable = true)
    lateinit var zipCode: String

    // 身份证
    @Column(name = "id_card", nullable = true)
    lateinit var idCard: String

    // 用户简介
    lateinit var summary: String

    // 职位
    @ManyToOne()
    @JoinColumn(name = "user_position")
    lateinit var position: PositionsEntity

    // 单位详情
    @ManyToOne()
    @JoinColumn(name = "user_details")
    lateinit var unitDetails: UnitDetailsEntity


}