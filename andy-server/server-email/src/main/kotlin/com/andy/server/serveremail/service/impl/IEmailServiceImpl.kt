package com.andy.server.serveremail.service.impl

import com.andy.andycommonbean.bean.EmailBean
import com.andy.andycommonutils.RandomUtil
import com.andy.server.serveremail.dao.EmailDao
import com.andy.server.serveremail.entity.EmailEntity

import com.andy.server.serveremail.service.IEmailService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
class IEmailServiceImpl: IEmailService {


    private val log: Logger = LoggerFactory.getLogger(IEmailServiceImpl::class.java)

    @Autowired
    private lateinit var jms: JavaMailSender

    @Autowired
    private lateinit var emailDao: EmailDao

    @Value("\${email.activeTime}")
    private lateinit var ACTIVETIME: String

    @Transactional
    override fun sendEmtail(emailBean: EmailBean): EmailBean {
        val mail = SimpleMailMessage()
        mail.setFrom(emailBean.sender)
        mail.setTo(emailBean.receiver)
        mail.setSubject(emailBean.subject)
        mail.setText(emailBean.text)
        jms.send(mail)
//        return emailBean.run {
//            val emailEntity = EmailEntity()
//            emailEntity.sender = emailBean.sender
//            emailEntity.receiver = emailBean.receiver
//            emailEntity.subject = emailBean.subject
//            emailEntity.text = emailBean.text
//            val save = emailDao.save(emailEntity)
//            this.eid = save.id.toString()
//            return@run this
//        }
        return emailBean
    }

    override fun emailValid(code: String, email: String): Boolean {
        val emailEntity = emailDao.findLastByEmail(email)
        return emailEntity.map {
            val now = ZonedDateTime.now()
            val createdDate = it.createdDate
            if (createdDate.year == now.year
                    && createdDate.dayOfYear == now.dayOfYear
                    && createdDate.hour == now.hour
                    && (createdDate.minute - now.minute) > ACTIVETIME.toInt())
                return@map false
            return@map true
        }.orElse(false)
    }

    @Transactional
    override fun sendEmailByIdentifyCode(emailBean: EmailBean): Boolean {
        emailBean.text =  RandomUtil.generteSixNumber().toString()
        val mail = SimpleMailMessage()
        mail.setFrom(emailBean.sender)
        mail.setTo(emailBean.receiver)
        mail.setSubject(emailBean.subject)
        mail.setText(emailBean.text)
        jms.send(mail)
//        return emailBean.run {
//            val emailEntity = EmailEntity()
//            emailEntity.sender = emailBean.sender
//            emailEntity.receiver = emailBean.receiver
//            emailEntity.subject = emailBean.subject
//            emailEntity.text = emailBean.text
//            val save = emailDao.save(emailEntity)
//            this.eid = save.id.toString()
//            return@run true
//        }.or(false)
        return false
    }
}