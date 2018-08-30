package com.andy.service.servierusercenter.bean


/**
 * describe: 注册信息的Bean
 * author 候帅
 * date 2018/8/24 下午2:46
 */
class RegisterInfoBean {

    lateinit var account: String

    lateinit var password: String

    var email: String? = null

    var tel: String? = null

    lateinit var nickName: String

    // 验证码
    var code: String? = null

    override fun toString(): String {
        return "RegisterInfoBean(account='$account', password='$password', email='$email', tel='$tel', nickName='$nickName', code='$code')"
    }


}