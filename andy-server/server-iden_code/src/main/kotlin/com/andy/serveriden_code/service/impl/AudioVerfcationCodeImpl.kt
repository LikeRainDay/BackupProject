package com.andy.serveriden_code.service.impl

import com.andy.serveriden_code.config.VerificationCodeProerties
import com.andy.serveriden_code.service.IVCodeGenerationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.OutputStream


/**
 * describe: 音频验证码服务
 * author 候帅
 * date 2018/8/29 下午2:08
 */
@Service
class AudioVerfcationCodeImpl: IVCodeGenerationService {

    private val log: Logger = LoggerFactory.getLogger(AudioVerfcationCodeImpl::class.java)


    @Autowired
    private lateinit var proerties: VerificationCodeProerties

    override fun generationByText(text: String, stream: OutputStream) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun randomGeneraionLimitNumber(number: Int, stream: OutputStream) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    
    override fun randomGeneration(stream: OutputStream) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}