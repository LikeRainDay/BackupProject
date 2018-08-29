package com.andy.serveriden_code.service

import java.io.OutputStream

/**
 * describe: 验证码生成服务
 * author 候帅
 * date 2018/8/29 下午1:51
 */
interface IVCodeGenerationService {


    /**
     * describe: 进行随机生成验证码
     * author 候帅
     * date 2018/8/29 下午1:51
     */
    fun randomGeneration(stream: OutputStream)
    
    /**
     * describe: 进行生成几位的验证码
     * author 候帅  
     * date 2018/8/29 下午2:11
     * @param number 限定验证码长度
     * @return   返回的流
     */
    fun randomGeneraionLimitNumber(number: Int, stream: OutputStream)

    /**
     * describe: 指定生成的内容
     * author 候帅
     * date 2018/8/29 下午2:13
     * @param text 指定要生成的内容
     * @return   返回的流
     */
    fun generationByText(text: String, stream: OutputStream)


}