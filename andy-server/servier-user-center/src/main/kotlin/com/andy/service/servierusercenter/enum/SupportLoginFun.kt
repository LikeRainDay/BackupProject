package com.andy.service.servierusercenter.enum

/**
 * describe: 可使用的登录方式
 * author 候帅
 * date 2018/8/24 下午2:17
 */
enum class SupportLoginFun(var value: Int) {

    ACCOUNT(value = 0),

    EMAIL(value = 1),

    TEL(value = 2),

    QQ(value = 3),

    WX(value = 4),

    WB(value = 5),

    QR360(value = 6),

    WY(value = 7)

}