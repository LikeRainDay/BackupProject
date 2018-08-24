package com.andy.service.servierusercenter.exception

import org.slf4j.LoggerFactory

/**
 * describe: 重复的账号异常
 * author 候帅
 * date 2018/8/24 下午10:20
 */
class RepeatAccountException: RuntimeException {

    private val Log = LoggerFactory.getLogger(NotFoundAccountException::class.java)

    companion object {
        fun Error(): RepeatAccountException {
            return RepeatAccountException("Account number repetition")
        }
    }


    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}