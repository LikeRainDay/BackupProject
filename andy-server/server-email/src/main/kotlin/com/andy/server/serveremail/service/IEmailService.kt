package com.andy.server.serveremail.service

import com.andy.andycommonbean.bean.EmailBean

/**
 * describe: 邮件服务
 * author 候帅
 * date 2018/8/25 下午4:38
 */
interface IEmailService {

    /**
     * describe: 发送简易方式的邮件
     * author 候帅
     * date 2018/8/25 下午4:38
     * @param emailBean 邮件
     * @return 返回邮件内容
     */
    fun sendEmtail(emailBean: EmailBean): EmailBean


    /**
     * describe: 发送验证码到邮箱
     * author 候帅
     * date 2018/8/25 下午10:23
     * @param emailBean 邮件信息
     * @return 发送对应邮件信息
     */
    fun sendEmailByIdentifyCode(emailBean: EmailBean): Boolean


    /**
     * describe: 验证邮件中的短信是否  在有效时间内
     * author 候帅
     * date 2018/8/26 下午1:33
     * @param code 验证码
     * @param account 账号
     * @return 是否成功
     */
    fun emailValid(code: String, email: String): Boolean

    /**
     * describe: 发送邮件验证码
     * author 候帅
     * date 2018/9/29 下午4:50
     * @param
     * @return
     */
    fun sendIdentifyCode(receiver: String)

}
