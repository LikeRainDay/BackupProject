package com.andy.andycommonutils

import java.util.*

/**
 * describe: 随机内容生成
 * author 候帅  
 * date 2018/8/25 上午8:51  
 */  
object RandomUtil {
    
    
    /**
     * describe: 生成6位随机字符
     * author 候帅  
     * date 2018/8/25 上午8:52  
     * @return 6位随机数字
     */
    fun generteSixNumber(): Int {
        return ((Math.random()*9+1)*100000).toInt()
    }

    /**
     * describe: 生成UUID的32位验证
     * author 候帅
     * date 2018/9/5 下午10:16
     * @return 生成32随机数
     */
    fun generteRandomUUID(): String {
        return UUID.randomUUID().toString().replace("-", "")
    }
    
}