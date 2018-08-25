package com.andy.server.serveremail.service.impl

import com.andy.andycommonbean.bean.EmailBean
import com.andy.server.serveremail.dao.EmailDao
import com.andy.server.serveremail.entity.EmailEntity

import com.andy.server.serveremail.service.IEmailService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IEmailServiceImpl: IEmailService {

    private val log: Logger = LoggerFactory.getLogger(IEmailServiceImpl::class.java)

    @Autowired
    private lateinit var jms: JavaMailSender

    @Autowired
    private lateinit var emailDao: EmailDao

    @Transactional
    override fun sendEmtail(emailBean: EmailBean): EmailBean {
        val mail = SimpleMailMessage()
        mail.setFrom(emailBean.sender)
        mail.setTo(emailBean.receiver)
        mail.setSubject(emailBean.subject)
        mail.setText(emailBean.text)
        jms.send(mail)
        return emailBean.run {
            val emailEntity = EmailEntity()
            emailEntity.sender = emailBean.sender
            emailEntity.receiver = emailBean.receiver
            emailEntity.subject = emailBean.subject
            emailEntity.text = emailBean.text
            val save = emailDao.save(emailEntity)
            this.eid = save.id.toString()
            return@run this
        }
    }

}