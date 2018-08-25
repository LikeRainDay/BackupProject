package com.andy.server.serveiersms.entity

import com.andy.corejpa.AbstractIdAuditable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Index
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


/**
 * describe: 短信相关实体内容
 * author 候帅
 * date 2018/8/25 上午9:57
 */
@Entity
@Table(name = "sms",
        indexes = [
            Index(name = "INDEX_mobile", columnList = "mobile_code", unique = false)
        ])
class SmsEntity: AbstractIdAuditable() {


    @NotNull
    @Size(min = 11, max = 11)
    @Column(name = "mobile_code", nullable = false, length = 11)
    lateinit var mobile: String


    // 验证码
    @Size(min = 6, max = 6)
    @Column(name = "identify_code", nullable = false, length = 6)
    lateinit var identifyCode: String

}