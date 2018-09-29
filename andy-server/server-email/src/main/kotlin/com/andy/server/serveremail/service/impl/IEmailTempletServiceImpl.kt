package com.andy.server.serveremail.service.impl

import com.andy.andycommonutils.ResourceUril
import com.andy.server.serveremail.service.IEmailTempletService
import com.google.common.io.ByteStreams
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class IEmailTempletServiceImpl : IEmailTempletService {


    private val log: Logger = LoggerFactory.getLogger(IEmailTempletServiceImpl::class.java)


    override fun templateIdentityCode(code: String): String {
        val jsonString = ResourceUril.getFileContent("templet/tp_idc.html")
        return jsonString.replaceFirst("#haizhi-code#", code)
    }

    override fun templateConfirmIdentity(url: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun templateSubscribe(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun templateWelecome(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun templateNews(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun templateNewContent(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    /**
//     * describe: 提取resource下的文件内容
//     * author 候帅
//     * date 2018/9/29 下午5:22
//     */
//    @Throws(IOException::class)
//    private fun getFileContent(filePath: String): String {
//        val resourceAsStream = this.javaClass.classLoader.getResourceAsStream(filePath)
//        return String(ByteStreams.toByteArray(resourceAsStream))
//    }


}