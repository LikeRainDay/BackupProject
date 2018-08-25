package com.andy.service.servierusercenter.exception

import org.slf4j.LoggerFactory


class SmsException: RuntimeException {

    private val Log = LoggerFactory.getLogger(SmsException::class.java)

    companion object {
        fun Error(): SmsException {
            return SmsException("Short message verification code mismatch")
        }
    }

    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}