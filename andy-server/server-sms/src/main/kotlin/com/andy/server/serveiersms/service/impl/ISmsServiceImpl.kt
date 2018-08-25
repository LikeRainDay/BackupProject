package com.andy.server.serveiersms.service.impl

import com.andy.andycommonbean.bean.SmsBean
import com.andy.andycommonbean.response.BaseResponse
import com.andy.andycommonutils.RandomUtil
import com.andy.andycommonutils.RestfulUtil
import com.andy.server.serveiersms.dao.SmsDao
import com.andy.server.serveiersms.entity.SmsEntity
import com.andy.server.serveiersms.exception.RepeatIdentifyException
import com.andy.server.serveiersms.service.ISmsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert
import org.springframework.util.LinkedMultiValueMap
import java.time.ZonedDateTime

/**
 * describe: 聚合SMS 的实现方式 （API接口地址为： https://www.juhe.cn/docs/api/id/54）
 * author 候帅
 * date 2018/8/25 上午8:58
 */
@Service
class ISmsServiceImpl: ISmsService{


    @Value("\${sms.Appkey}")
    private lateinit var APP_KEY: String

    @Value("\${sms.TemplateId}")
    private lateinit var TEMPLATE_ID: String

    @Value("\${sms.url}")
    private lateinit var URL: String

    @Value("\${sms.activeTime}")
    private lateinit var ACTIVETIME: String

    @Autowired
    private lateinit var smsDao: SmsDao


    companion object {
        const val PREFIX: String = "#code#="
    }

    @Throws(RepeatIdentifyException::class)
    @Transactional
    override fun sendMessage(mobile: String): String? {
        val mobileEntity = smsDao.findSmsByMobile(mobile)

        return mobileEntity.map {
            val now = ZonedDateTime.now()
            val createdDate = it.createdDate
            if (createdDate.year == now.year
                    && createdDate.dayOfYear == now.dayOfYear
                    && (createdDate.minute - now.minute) < ACTIVETIME.toInt())
                throw RepeatIdentifyException.Error()
            return@map sendIdenCodeMessage(mobile)
        }.orElseGet {
            return@orElseGet sendIdenCodeMessage(mobile)
        }
    }
    override fun identiftyCodeSuccess(mobile: String, code: String): Boolean {
        val sms = smsDao.findSmsByMobile(mobile)
        return sms.map {
            return@map it.identifyCode == code
        }.orElse(false)
    }

    /**
     * describe: 发送短信 并讲内容进行写入数据库
     * author 候帅
     * date 2018/8/25 下午12:01
     */
    private fun sendIdenCodeMessage(phone: String): String? {
        val params = LinkedMultiValueMap<String, Any>()
        val generteSixNumber = RandomUtil.generteSixNumber()
        params["mobile"] = phone
        params["tpl_id"] = TEMPLATE_ID
        params["tpl_value"] = PREFIX + generteSixNumber
        params["key"] = APP_KEY
        val body = RestfulUtil.sendPost(URL, params).body
        val smsEntity = SmsEntity()
        smsEntity.identifyCode = generteSixNumber.toString()
        smsEntity.mobile = phone
        smsDao.save(smsEntity)
        return body
    }

}