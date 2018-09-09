package com.andy.service.servierusercenter.util

import com.andy.andycommonutils.RegexUtil

/**
 * describe: 账号相关的工具类
 * author 候帅  
 * date 2018/8/24 上午10:05  
 */  
object AccountUtil {

    enum class AccountType{
        EMAIL,
        USERNAME,
        TEL,
        UNKNOWN
    }

    
    /**
     * describe: 判断当前的账号来源为
     * author 候帅  
     * date 2018/8/24 上午10:06  
     */
    fun judgeAccountType(account: String): AccountType {
        // 正则匹配当前是否为邮箱
        return if (RegexUtil.isEmail(account))
            AccountType.EMAIL
        else if (RegexUtil.isPhone(account))
            AccountType.TEL
        else if (RegexUtil.isLegalAccount(account))
            AccountType.USERNAME
        else
            AccountType.UNKNOWN
    }


   /**
    * describe: 对应账号来源 则回调
    * author 候帅
    * date 2018/8/24 上午10:30
    */
   fun judgeAccountTypeByListener(account: String
                                  , AccountListener: () -> Unit
                                  , PhoneListener: () -> Unit
                                  , emailListener: () -> Unit
                                  , UNKNOWListener: () -> Unit) {
       when {
           RegexUtil.isEmail(account) -> emailListener.invoke()
           RegexUtil.isPhone(account) -> PhoneListener.invoke()
           RegexUtil.isLegalAccount(account) -> AccountListener.invoke()
           else -> UNKNOWListener.invoke()
       }
   }



    
}