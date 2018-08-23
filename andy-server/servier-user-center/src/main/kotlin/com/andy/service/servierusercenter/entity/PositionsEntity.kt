package com.andy.service.servierusercenter.entity

import com.andy.corejpa.AbstractIdAuditable
import javax.persistence.*

/**
 * FileName: PositionsEntity
 * author:   候帅
 * data:     22/08/2018 22:59
 * Description: 职位实体
 * History:
 */
@Entity
@Table(name = "positions",
        indexes = [
            Index(name = "INDEX_positionCoding", columnList = "positionCoding"),
            Index(name = "INDEX_positionIndex", columnList = "positionIndex")
        ])
class PositionsEntity: AbstractIdAuditable(){

    // 职位
    lateinit var position: String

    // 上级
    lateinit var unitName: String

    // 职位编号
    lateinit var positionCoding: String

    // 索引
    lateinit var positionIndex: String


    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER)
    lateinit var userDetails: Set<UserDetailsEntity>

}