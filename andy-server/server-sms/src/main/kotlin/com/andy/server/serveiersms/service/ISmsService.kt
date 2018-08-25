package com.andy.server.serveiersms.service



/**
 * describe: 短信服务
 * author 候帅  
 * date 2018/8/25 上午7:47  
 */  
interface ISmsService {

    /**
     * describe: 短信服务
     * author 候帅  
     * date 2018/8/25 上午7:47  
     * @param phone 电话号码
     * @param message 消息
     * @return  消息返回的内容
     */
    fun sendMessage(mobile: String): String?

    /**
     * describe: 是否验证码正确
     * author 候帅
     * date 2018/8/25 下午3:26
     * @param
     * @return
     */
    fun identiftyCodeSuccess(mobile: String, code: String): Boolean
}