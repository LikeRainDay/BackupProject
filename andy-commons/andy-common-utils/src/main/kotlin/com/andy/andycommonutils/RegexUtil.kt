package com.andy.andycommonutils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * describe: 正则相关工具
 * author 候帅
 * date 2018/8/24 上午10:14
 */
object RegexUtil {

    private val log: Logger = LoggerFactory.getLogger(RegexUtil::class.java)

    // 账号正则
    const val ACCOUNT_REGEX: String = "^[A-Za-z0-9_]*$"
    // 电话正则
    private const val PHONE_REGEX: String = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$"
    // 邮箱正则
    private const val EMAIL_REGEX: String = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"


    /**
     * describe: 是否为电话号码
     * author 候帅
     * date 2018/8/24 上午10:15
     */
    fun isPhone(phone: String): Boolean {
        val regex = PHONE_REGEX
        return if (phone.length != 11) {
            false
        } else {
            val p = Pattern.compile(regex)
            val m = p.matcher(phone)
            m.matches()
        }
    }


    /**
     * describe: 是否有邮箱
     * author 候帅
     * date 2018/8/24 上午10:21
     */
    fun isEmail(email: String): Boolean {
        val regEx1 = EMAIL_REGEX
        val p: Pattern
        val m: Matcher
        p = Pattern.compile(regEx1)
        m = p.matcher(email)
        return m.matches()
    }


    /**
     * describe: 是否为合法账号
     * author 候帅
     * date 2018/8/24 上午10:22
     */
    fun isLegalAccount(account: String): Boolean{
        val regEx1 = ACCOUNT_REGEX
        val p: Pattern
        val m: Matcher
        p = Pattern.compile(regEx1)
        m = p.matcher(account)
        return m.matches()
    }

}