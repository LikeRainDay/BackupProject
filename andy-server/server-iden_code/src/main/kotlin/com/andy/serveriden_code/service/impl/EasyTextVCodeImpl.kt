package com.andy.serveriden_code.service.impl

import com.andy.serveriden_code.config.VCodeStyleProerties
import com.andy.serveriden_code.service.IVCodeGenerationService
import com.andy.serveriden_code.util.RandomChineseUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.OutputStream
import java.util.*
import javax.imageio.ImageIO


/**
 * describe: 图片验证码服务
 * author 候帅
 * date 2018/8/29 下午2:08
 */
@Service(value = "EasyPicTextVC")
class EasyTextVCodeImpl : IVCodeGenerationService {

    @Autowired
    private lateinit var styleProerties: VCodeStyleProerties

    override fun randomGeneraionLimitNumber(number: Int, stream: OutputStream) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generationByText(text: String, stream: OutputStream) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private val log: Logger = LoggerFactory.getLogger(EasyTextVCodeImpl::class.java)

    // TODO 需要进行抽象出来
    override fun randomGeneration(stream: OutputStream) {
        val random = Random()
        val image = BufferedImage(styleProerties.width, styleProerties.height, BufferedImage.TYPE_INT_RGB)
        val g = image.graphics as Graphics2D

        val resourceAsStream =  EasyTextVCodeImpl::class.java.classLoader.getResourceAsStream("bg/bg1.jpg")

        g.drawImage(ImageIO.read(resourceAsStream), 0, 0, styleProerties.width, styleProerties.height, null)
        // 设置颜色
        g.color = Color.white
        // 画边框
        g.drawRect(0, 0, styleProerties.width - 1, styleProerties.height - 1)
        // 设置字体
        g.font = Font("宋体", Font.BOLD, 20)
        // 用于记录坐标
        var x: Int
        var y: Int
        var target: String
        for (i in 0 until styleProerties.defaultLength) {
            g.color = Color(random.nextInt(50) + 200, random.nextInt(150) + 100, random.nextInt(50) + 200)
            val str = RandomChineseUtil.getRandomChar()
            val a = random.nextInt(styleProerties.width - 100) + 50
            val b = random.nextInt(styleProerties.width - 70) + 55
            g.drawString(str, a, b)
        }
        g.color = Color.white
        g.dispose()
        ImageIO.write(image, "jpg", stream)
    }
}