package com.andy.service.servierusercenter.service

import com.andy.service.servierusercenter.entity.UserDetailsEntity
import java.util.*


interface IUserDetailsService {

    /**
     * describe: 进行存储当前的用户详情信息
     * author 候帅
     * date 2018/8/25 下午3:57
     * @param userDetails 用户详情
     * @return 存储到数据库中的用户详情数据
     */
    fun save(userDetails: UserDetailsEntity): Optional<UserDetailsEntity>
}