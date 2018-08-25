package com.andy.andycommonbean.bean

import java.io.Serializable

/**
 * describe: 短信实体内容
 * author 候帅
 * date 2018/8/25 下午4:40
 */
class EmailBean: Serializable {

    lateinit var eid: String

    lateinit var text: String

    lateinit var receiver: String

    lateinit var sender: String

    lateinit var subject: String
}