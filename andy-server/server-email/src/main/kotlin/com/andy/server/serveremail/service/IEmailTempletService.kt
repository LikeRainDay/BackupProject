package com.andy.server.serveremail.service


/**
 * describe: 邮件模板
 * author 候帅
 * date 2018/9/29 下午4:59
 */
interface IEmailTempletService {


    /**
     * describe: 验证码模板
     * author 候帅
     * date 2018/9/29 下午5:54
     */
    fun templateIdentityCode(code: String): String


    /**
     * describe: 确认邮件模板
     * author 候帅
     * date 2018/9/29 下午5:55
     */
    fun templateConfirmIdentity(url: String): String


    /**
     * describe: 订阅的消息模板
     * author 候帅
     * date 2018/9/29 下午5:56
     */
    fun templateSubscribe(): String


    /**
     * describe: 欢迎注册模板
     * author 候帅
     * date 2018/9/29 下午5:58
     */
    fun templateWelecome(): String

    /**
     * describe: 新闻模板
     * author 候帅
     * date 2018/9/29 下午6:02
     */
    fun templateNews(): String

    /**
     * describe: 新内容模板
     * author 候帅
     * date 2018/9/29 下午6:02
     */
    fun templateNewContent(): String

}