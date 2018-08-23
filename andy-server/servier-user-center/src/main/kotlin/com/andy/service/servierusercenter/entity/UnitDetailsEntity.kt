package com.andy.service.servierusercenter.entity

import javax.persistence.*
import javax.validation.constraints.Size

/**
 * FileName: UnitDetailsEntity
 * author:   候帅
 * data:     22/08/2018 22:36
 * Description: 单位信息
 * History:
 */
@Entity
@Table(name = "unit_details")
class UnitDetailsEntity: AbstractEntity() {

    // 单位
    lateinit var unitName: String

    // 单位地址
    lateinit var unitAdress: String

    // 单位编号
    lateinit var unitCoding: String

    // 单位电话
    lateinit var unitPhone: String

    //单位邮编
    lateinit var unitZipCode: String

    //单位税号
    lateinit var unitTaxNumber: String

    // 单位法人
    lateinit var legalPersion: String

    // 用户详情映射
    @OneToMany(mappedBy = "unitDetails", fetch = FetchType.EAGER)
    lateinit var userDetails: Set<UserDetailsEntity>

    // 单位描述
    @Size(max = 250)
    @Column(name = "unit_des")
    lateinit var unitDes: String

    // 单位状态
    var status: Int = 0

    // 上级单位id   又没上级单位则为Null
    lateinit var superiorUnit: String
}