package com.andy.service.servierusercenter.bean


/**
 * describe: 注册信息的Bean
 * author 候帅
 * date 2018/8/24 下午2:46
 */
class RegisterInfoBean {

    lateinit var account: String

    lateinit var password: String

    lateinit var email: String

    lateinit var tel: String

    lateinit var nickName: String

    // 验证码
    lateinit var code: String
}