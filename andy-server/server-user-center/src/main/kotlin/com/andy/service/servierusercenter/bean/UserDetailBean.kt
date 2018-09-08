package com.andy.service.servierusercenter.bean


/**
 * describe: 用户的基本信息
 * author 候帅
 * date 2018/8/24 上午11:12
 */
data class UserDetailBean(

        var userId: String,

        var sex: Int = 1,

        var avator: String? = null,

        var nickName: String? = null,

        var zipCode: String? = null,

        var tel: String? = null,

        var email: String? = null,

        var idCard: String? = null,

        var summary: String? = null,

        var adress: String? = null)