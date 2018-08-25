package com.andy.andycommonbean.bean

import java.io.Serializable

/**
 * describe:
 * author 候帅
 * date 2018/8/25 下午2:00
 */
class SmsBean: Serializable {

    // 发送条数
    var count: Int = 0

    // 扣除条数
    var fee: Int = 0

    // 短信ID
    lateinit var sid: String

}