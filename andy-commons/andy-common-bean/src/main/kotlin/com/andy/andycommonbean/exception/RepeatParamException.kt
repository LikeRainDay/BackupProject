package com.andy.andycommonbean.exception

import org.slf4j.LoggerFactory

/**
 * describe: 重复的账号异常
 * author 候帅
 * date 2018/8/24 下午10:20
 */
class RepeatParamException : RuntimeException {

    private val Log = LoggerFactory.getLogger(RepeatParamException::class.java)

    companion object {
        fun error(): RepeatParamException {
            return RepeatParamException("param is repeat")
        }

        fun error(message: String): RepeatParamException {
            return RepeatParamException(message)
        }
    }


    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}