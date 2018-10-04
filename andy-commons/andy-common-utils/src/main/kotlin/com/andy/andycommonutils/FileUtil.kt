package com.andy.andycommonutils

import com.google.common.io.CharStreams
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * describe: 文件和字节流相关的
 * author 候帅
 * date 2018/9/29 下午6:08
 */
object FileUtil {


    /**
     * describe: 将输入流转化为字符串
     * author 候帅
     * date 2018/9/29 下午6:14
     * @param inputStream 输入流
     * @return   字符串
     */
    fun InputStreamToString(inputStream: InputStream): String {
//        val result = ByteArrayOutputStream()
//        val buffer = ByteArray(1024)
//        var length: Int = 0
//        val read = inputStream.read(buffer)
//        while (length == read) {
//            result.write(buffer, 0, length)
//        }
//        return result.toString(StandardCharsets.UTF_8.name())
        return CharStreams.toString(InputStreamReader(inputStream, StandardCharsets.UTF_8))
    }
}