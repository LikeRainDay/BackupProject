package com.andy.serveriden_code.service.impl

import com.andy.serveriden_code.service.IVCodeGenerationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.OutputStream

/**
 * describe: 计算验证码生成
 * author 候帅
 * date 2018/8/29 下午5:42
 */
@Service(value = "CaptcahaVC")
class CaptchaVCodeImpl: IVCodeGenerationService {

    private val log: Logger = LoggerFactory.getLogger(CaptchaVCodeImpl::class.java)


    override fun randomGeneration(stream: OutputStream) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun randomGeneraionLimitNumber(number: Int, stream: OutputStream) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generationByText(text: String, stream: OutputStream) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}