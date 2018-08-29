package com.andy.serveriden_code.util

import org.apache.commons.codec.Charsets
import java.util.*
import java.io.UnsupportedEncodingException



/**
 * describe: 随机中文汉字工具
 * author 候帅
 * date 2018/8/29 下午1:53
 */
object RandomChineseUtil {


    /**
     * describe: 进行随机生成中文汉字
     * author 候帅
     * date 2018/8/29 下午2:05
     * @return 随机的中文汉字
     */
    fun getRandomChar(): String {
        var str = ""
        val highCode: Int
        val lowCode: Int

        val random = Random()

        highCode = 176 + Math.abs(random.nextInt(39)) //B0 + 0~39(16~55) 一级汉字所占区
        lowCode = 161 + Math.abs(random.nextInt(93)) //A1 + 0~93 每区有94个汉字

        val b = ByteArray(2)
        b[0] = Integer.valueOf(highCode).toByte()
        b[1] = Integer.valueOf(lowCode).toByte()

        try {
            str = String(b, Charsets.toCharset("GBK"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return str


    }
}