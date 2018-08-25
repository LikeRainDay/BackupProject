package com.andy.andycommonutils

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
    
}