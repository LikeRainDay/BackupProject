package com.andy.serveriden_code.service

import java.io.OutputStream

/**
 * describe: CNN生成验证码相关的定制服务
 * author 候帅
 * date 2018/8/29 下午2:17
 */
interface IStyleGeneraionService: IVCodeGenerationService{

    /**
     * describe: 自定义生成风格
     * author 候帅
     * date 2018/8/29 下午2:22
     */
    fun customVcStyle(styleFilePath: String, textPicPath: String, test: String, stream: OutputStream)
}