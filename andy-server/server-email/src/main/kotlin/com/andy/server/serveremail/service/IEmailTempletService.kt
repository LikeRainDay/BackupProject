package com.andy.server.serveremail.service



/**
 * describe: 邮件模板
 * author 候帅
 * date 2018/9/29 下午4:59
 */
interface IEmailTempletService {

    fun getIdentityCodeTemplate(code: String): String


}