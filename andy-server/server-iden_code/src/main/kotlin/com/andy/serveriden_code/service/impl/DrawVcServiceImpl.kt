package com.andy.serveriden_code.service.impl

import com.andy.serveriden_code.service.DrawVcService
import com.andy.serveriden_code.util.VcUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.OutputStream

/**
 * describe: 进行绘制操作、
 * author 候帅
 * date 2018/8/29 下午5:18
 */
@Service
class DrawVcServiceImpl : DrawVcService {


    private val log: Logger = LoggerFactory.getLogger(DrawVcServiceImpl::class.java)


    override fun generationVC(code: String, stream: OutputStream) {
        VcUtil.NumberVC(stream)
    }

    override fun generationEasyVC(code: String, stream: OutputStream, bgUrls: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generationCnnVc(code: String, stream: OutputStream, charactersBgUrls: List<String>, StyleBgUrls: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generationCaptchaVc(stream: OutputStream): Int {
        return VcUtil.CaptchaVC(stream)
    }

}