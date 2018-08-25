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
     * @param
     * @return
     */
    fun sendEmtail(emailBean: EmailBean): EmailBean


}