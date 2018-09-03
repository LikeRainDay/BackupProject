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

    /**
     * describe: 修改用户的详情信息
     * author 候帅
     * date 2018/9/1 下午6:57
     * @param userDetails 待修改的用户详情信息
     */
    fun modiftyUserDetails(userDetails: UserDetailsEntity)


    /**
     * describe: 查询用户的详情信息
     * author 候帅
     * date 2018/9/1 下午6:59
     * @param userDetailsId 用户详情的ID
     * @return   用户详情信息
     */
    fun findUserDetailsById(userDetailsId: String): UserDetailsEntity

}