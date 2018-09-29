package com.andy.andycommonutils

import java.io.IOException


/**
 * describe: 资源目录相关的操作
 * author 候帅
 * date 2018/9/29 下午6:05
 */
object ResourceUril {

    /**
     * describe: 进行读取资源目录下的文件内容
     * author 候帅
     * date 2018/9/29 下午6:05
     * @param filePath 资源文件下的文件路径
     * @return   读取出来的文件内容
     */
    @Throws(IOException::class)
    fun getFileContent(filePath: String): String {
        val resourceAsStream = this.javaClass.classLoader.getResourceAsStream(filePath)
        return FileUtil.InputStreamToString(resourceAsStream)
    }
}