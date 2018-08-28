package com.andy.server.serveiersms.dao

import com.andy.server.serveiersms.entity.SmsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

/**
 * describe: 短信相关记录
 * author 候帅
 * date 2018/8/25 上午11:07
 */
interface SmsDao: JpaRepository<SmsEntity, Long> {

    /**
     * describe: 查询对应手机号 按日期排序
     * author 候帅
     * date 2018/8/25 上午11:09
     * @param mobile 手机号
     * @return 短信内容
     */
    fun findFirstByMobileOrderByCreatedDate(mobile: String): Optional<SmsEntity>



}