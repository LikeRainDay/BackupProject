package com.andy.ecologyoauth2.service


/**
 * describe: 验证Access_token的有效性，并进行相应的操作
 * author 候帅
 * date 2018/10/1 下午9:58
 */
interface CheckTokenService {

    /**
     * describe: 验证AccessToken的有效性，并获取用户的详细信息内容
     * author 候帅
     * date 2018/10/1 下午10:00
     */
    fun checkToken(token: String): Any


}