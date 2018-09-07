package com.andy.service.servierusercenter.exception

import com.andy.service.servierusercenter.util.AccountUtil
import org.slf4j.LoggerFactory


/**
 * describe: 未发现当前的账号内容
 * author 候帅
 * date 2018/8/24 上午11:04
 */

class NotFoundAccountException : RuntimeException {

    private val Log = LoggerFactory.getLogger(NotFoundAccountException::class.java)

    companion object {
        fun Error(): NotFoundAccountException {
            return NotFoundAccountException("This type of account was not found")
        }

        fun Error(account: String, type: AccountUtil.AccountType = AccountUtil.AccountType.UNKNOWN): NotFoundAccountException {
            return NotFoundAccountException("${type.name} of $account was not found")
        }
    }


    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}


}
