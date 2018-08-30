package com.andy.andycommonbean.response

import java.io.Serializable
import java.util.*
import javax.validation.constraints.NotNull


/**
 * describe: 响应内容
 * author 候帅
 * date 2018/8/25 下午12:04
 */
open class BaseResponse: Serializable {


    /**
     * describe: 消息跟踪日志
     * author 候帅
     * date 2018/8/25 下午12:06
     */
    @NotNull
    var logId: String = UUID.randomUUID().toString()

    /**
     * describe: code
     * author 候帅
     * date 2018/8/25 下午12:07
     */
    @NotNull
    var code: Int = 0

    /**
     * describe: 消息提示
     * author 候帅
     * date 2018/8/25 下午12:09
     */
    @NotNull
    lateinit var reason: String


}