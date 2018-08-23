package com.andy.service.servierusercenter.entity

import javax.persistence.*

/**
 * FileName: UserDetailsEntity
 * author:   候帅
 * data:     21/08/2018 21:24
 * Description: 用户详情信息
 * History:
 */
@Entity
@Table(name = "user_details")
class UserDetailsEntity: AbstractUserDetailsEntity() {



    // 用户操作历史关联
    @OneToMany(mappedBy = "userDetails", fetch = FetchType.EAGER)
    lateinit var userDetailsHistory: Set<UserDetailsHistoryEntity>
}