package com.andy.serveriden_code.service.impl

import com.andy.serveriden_code.service.IStyleGeneraionService
import com.andy.serveriden_code.service.IVCodeGenerationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.OutputStream


/**
 * describe: 卷积网络生成的验证码
 * author 候帅
 * date 2018/8/29 下午2:08
 */
@Service
class CNNPicTextVerfcationCodeImpl: IStyleGeneraionService {
    override fun customVcStyle(styleFilePath: String, textPicPath: String, test: String): OutputStream {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun randomGeneraionLimitNumber(number: Int): OutputStream {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generationByText(text: String): OutputStream {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private val log: Logger = LoggerFactory.getLogger(CNNPicTextVerfcationCodeImpl::class.java)
    
    
    override fun randomGeneration(): OutputStream {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}