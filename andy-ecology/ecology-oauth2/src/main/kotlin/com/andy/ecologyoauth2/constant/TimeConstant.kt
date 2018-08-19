package com.andy.ecologyoauth2.constant

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * describe: 时间常量的管理
 * author 候帅
 * date 2018/8/19 上午11:26
 */
class TimeConstant {

    companion object {

        // 刷新时间默认时长
        const val REFRESH_TOKEN_SECOUND = 100000L

        // token 有效时长
        const val ACCESS_TOKEN_SECOUND = 3600L
    }


}