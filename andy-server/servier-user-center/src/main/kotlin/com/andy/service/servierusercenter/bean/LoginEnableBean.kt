package com.andy.service.servierusercenter.bean



/**
 * describe: 可使用的登录方式
 * author 候帅
 * date 2018/8/24 上午9:26
 */
class LoginEnableBean {
    // 邮箱登录
    var email: Boolean = false
    // 电话登录
    var tel: Boolean = false
    // QQ 登录
    var QQ: Boolean = false
    // 微信 登录
    var WX: Boolean = false
    // 微博 登录
    var WB: Boolean = false
    // 360
    var QR360: Boolean = false
    // 网易
    var WY: Boolean = false
    // 用户名
    var account: Boolean = false
}