package com.andy.service.servierusercenter.entity

import com.andy.corejpa.AbstractIdAuditable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*
import javax.validation.constraints.Size

/**
 * describe: 用户详情 抽象类
 * author 候帅
 * date 2018/8/23 下午2:05
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractUserDetailsEntity: AbstractIdAuditable() {


    // 用户性别 0: 男生  1: 女生
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
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = [CascadeType.DETACH, CascadeType.REFRESH])
    @JoinColumn(name = "user_position_id")
    lateinit var position: PositionsEntity

     //单位详情
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = [CascadeType.DETACH, CascadeType.REFRESH])
    @JoinColumn(name = "unit_details_id")
    lateinit var unitDetails: UnitDetailsEntity



}