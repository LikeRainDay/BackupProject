package com.andy.serveriden_code.service.impl

import com.andy.serveriden_code.service.IVCodeGenerationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.OutputStream


/**
 * describe: 数字验证码服务
 * author 候帅
 * date 2018/8/29 下午2:08
 */
@Service
class NumberVerfcationCodeImpl: IVCodeGenerationService {


    override fun randomGeneraionLimitNumber(number: Int): OutputStream {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generationByText(text: String): OutputStream {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private val log: Logger = LoggerFactory.getLogger(NumberVerfcationCodeImpl::class.java)
    
    
    override fun randomGeneration(): OutputStream {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}