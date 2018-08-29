package com.andy.serveriden_code.service

import java.io.OutputStream

/**
 * describe: 进行绘制对应的内容
 * author 候帅
 * date 2018/8/29 下午5:08
 */
interface DrawVcService {


    /**
     * describe: 绘制英文和数字验证码
     * author 候帅
     * date 2018/8/29 下午5:19
     * @param stream 输出流
     * @param code 验证码
     */
    fun generationVC(code: String, stream: OutputStream)

    /**
     * describe: 绘制中文验证码（简易）
     * author 候帅
     * date 2018/8/29 下午5:19
     * @param code 验证码
     * @param stream 输出流
     * @param bgUrls 图片的背景素材地址 （ 进行 随机抽取）
     */
    fun generationEasyVC(code: String, stream: OutputStream, bgUrls: List<String>)

    /**
     * describe: 进行绘制 CNN 卷积之后的验证码
     * author 候帅
     * date 2018/8/29 下午5:23
     * @param code 验证码
     * @param stream 输出的图片流
     * @param charactersBgUrls 需要和文字混合的背景（ 进行XOR 获取公有部分 ）再与 StyleBgUrls 做CNN 生成 融合后的验证码
     * @param StyleBgUrls  风格背景图片
     */
    fun generationCnnVc(code: String, stream: OutputStream, charactersBgUrls: List<String>, StyleBgUrls: List<String>)


    /**
     * describe: 绘制 计算方式的 验证码
     * author 候帅
     * date 2018/8/29 下午5:44
     * @param stream 输出的图片流
     * @return 返回计算结果
     */
    fun generationCaptchaVc(stream: OutputStream): Int

}