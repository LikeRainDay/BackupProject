package com.andy.serveriden_code.util

import java.io.OutputStream
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.util.*


/**
 * describe: 生成纯数字验证码
 * author 候帅  
 * date 2018/8/29 下午5:33  
 */
object VcUtil {

    /**
     * describe: 计算验证码
     * author 候帅  
     * date 2018/8/29 下午5:50  
     */  
    fun CaptchaVC(stream: OutputStream): Int {
        val bi = BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB)
        val g = bi.graphics
        val c = Color(200, 150, 255)
        g.color = c
        g.fillRect(0, 0, 68, 22)

        val op = "+-".toCharArray()
        val r = Random()
        var index: Int
        val len1 = op.size
        var result = 0
        var firstNum = 0
        var secondNum = 0
        var operation = '0'
        for (i in 0..3) {
            index = if (i != 1)
                r.nextInt(100)
            else
                r.nextInt(len1)

            g.color = Color(r.nextInt(88), r.nextInt(188), r.nextInt(255))
            when (i) {
                0 -> {
                    g.drawString(index.toString() + "", i * 15 + 3, 18)
                    firstNum = index
                }
                2 -> {
                    g.drawString(index.toString() + "", i * 15 + 3, 18)
                    secondNum = index
                }
                1 -> {
                    g.drawString(op[index] + "", i * 15 + 3, 18)
                    operation = op[index]
                }
                else -> g.drawString("=", i * 15 + 3, 18)
            }
        }

        when (operation) {
            '+' -> result = firstNum + secondNum
            '-' -> result = firstNum - secondNum
            '*' -> result = firstNum * secondNum
        }
        ImageIO.write(bi, "JPG", stream)
        return result
    }

    /**
     * describe: 进行数字验证码的生成
     * author 候帅  
     * date 2018/8/29 下午5:52
     */
    fun NumberVC(stream: OutputStream){

        val width = 68
        val height = 22

        val bi = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val g = bi.graphics

        val r = Random()

        g.color = Color.BLACK

        g.fillRect(0, 0, width, height)

        val x = r.nextInt(4)
        val x1 = width - r.nextInt(4)
        var y1: Int
        // 干扰线
        for (i in 0 until 10){
            g.color = Color.BLUE
            val y = r.nextInt(height - r.nextInt(4))
            y1 = r.nextInt(height - r.nextInt(4))
            g.drawLine(x, y, x1, y1)
        }

        g.font = Font(Font.SANS_SERIF, Font.PLAIN, (height * 0.8).toInt())
        // 写字符
        var fx:Int = 0
        for (i in 0 until 4){
            val fy = ((Math.random() * 0.3 + 0.6) * height).toInt()
            g.color =  Color.yellow
            g.drawString(1.toString(), fx, fy)
            val d:Int = ((width / "1".length) * (Math.random() * 0.3 + 0.8)).toInt()
            fx += d
        }


        shearX(g, width, height, Color.CYAN)
        shearY(g, width, height, Color.CYAN)
        g.dispose()
        ImageIO.write(bi, "JPG", stream)
    }


    /**
     * describe: 进行扭曲图片
     * author 候帅
     * date 2018/8/29 下午6:16
     * @param
     * @return
     */
    fun shearY(g: Graphics, w1: Int, h1: Int, color: Color) {
        val random = Random()
        val period = 2
        val frames = 1
        val phase = random.nextInt(2)
        for (i in 0 until w1 ){
            val d = period.shr(1) * Math.sin(i.toDouble() / period + (2.2831853071795862 * phase)/ frames)
            g.copyArea(i, 0, 1, h1, 0, d.toInt())
            g.color = color
            g.drawLine(i, d.toInt(), i, 0)
            g.drawLine(i, d.toInt() + h1, i , h1)
        }
    }

    fun shearX(g: Graphics, w1: Int, h1: Int, color: Color) {
        val random = Random()
        val period = random.nextInt(40) + 10 // 50;
        val frames = 20
        val phase = random.nextInt(2)
        for (i in 0 until w1 ){
            val d = period.shr(1) * Math.sin(i.toDouble() / period + (2.2831853071795862 * phase)/ frames)
            g.copyArea(i, 0, w1, 1, d.toInt(), 0)
            g.color = color
            g.drawLine(d.toInt(), i, 0, i)
            g.drawLine(d.toInt() + w1, i, w1 , i)
        }
    }

}