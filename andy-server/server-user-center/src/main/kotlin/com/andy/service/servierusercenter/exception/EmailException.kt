package com.andy.service.servierusercenter.exception

import org.slf4j.LoggerFactory


class EmailException: RuntimeException {

    private val Log = LoggerFactory.getLogger(EmailException::class.java)

    companion object {
        fun Error(): EmailException {
            return EmailException("Email verification code mismatch")
        }
    }

    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}