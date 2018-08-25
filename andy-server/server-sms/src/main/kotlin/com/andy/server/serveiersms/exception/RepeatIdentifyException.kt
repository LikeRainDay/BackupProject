package com.andy.server.serveiersms.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory


class RepeatIdentifyException:RuntimeException {

    private val log: Logger = LoggerFactory.getLogger(RepeatIdentifyException::class.java)
    companion object {
        fun Error(): RepeatIdentifyException {
            return RepeatIdentifyException("Account number repetition")
        }
    }


    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}