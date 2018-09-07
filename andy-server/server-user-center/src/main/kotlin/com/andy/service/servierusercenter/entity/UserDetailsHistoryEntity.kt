package com.andy.service.servierusercenter.entity

import javax.persistence.*

/**
 * describe: 用户详情修改历史表
 * author 候帅
 * date 2018/8/23 上午9:44
 */
@Entity
@Table(name = "user_details_history")
class UserDetailsHistoryEntity: AbstractUserDetailsEntity(){

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = [CascadeType.DETACH, CascadeType.REFRESH])
    @JoinColumn(name = "user_details_id")
    lateinit var userDetails: UserDetailsEntity

}