package com.andy.service.servierusercenter.bean


/**
 * describe: 用户的基本信息
 * author 候帅
 * date 2018/8/24 上午11:12
 */
data class UserDetailBean(

        var userId: String,

        var sex: Int,

        var avator: String?,

        var nickName: String?,

        var zipCode: String?,

        var tel: String?,

        var email: String?,

        var idCard: String?,

        var summary: String?,

        var adress: String?)