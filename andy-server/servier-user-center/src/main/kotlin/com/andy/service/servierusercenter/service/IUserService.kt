package com.andy.service.servierusercenter.service

import com.andy.andycommonbean.bean.UserBean
import com.andy.service.servierusercenter.bean.UserDetailBean
import com.andy.service.servierusercenter.bean.LoginEnableBean
import com.andy.service.servierusercenter.bean.RegisterInfoBean


/**
 * describe: 进行操作用户信息。
 * author 候帅
 * date 2018/8/24 上午9:05
 */
interface IUserService {

    /**
     * describe: 进行删除用户 （伪删除）
     * author 候帅
     * date 2018/8/24 上午9:13
     * @param id  当前用户的唯一ID
     */
    fun falseDeleteUserById(id: String)

    /**
     * describe: 校验 登录的用户名和账号是否匹配
     * author 候帅
     * date 2018/8/24 上午9:15
     * @param username 用户账号 ( 包含可用的 邮箱， 用户账号， 手机 )
     * @param pass
     * @return  成功返回用户的基本信息
     */
    fun loginByUsernameAndPass(username: String, pass: String): UserDetailBean

    /**
     * describe: 账号 当前支持的 登录方式
     * author 候帅
     * date 2018/8/24 上午9:25
     * @param userId 待查询的账号( 包含可用的 邮箱， 用户账号， 手机 )
     * @return   已开通的登录方式
     */
    fun loginEnableByUsername(userId: String): LoginEnableBean

    /**
     * describe: 修改 用户密码
     * author 候帅
     * date 2018/8/24 上午9:32
     * @param userId 当前登录的用户名( 包含可用的 邮箱， 用户账号， 手机 )
     * @param oldPass 旧密码
     * @param newPass 新密码
     * @return   是否更改成功
     */
    fun reviseUserPass(userId: String, oldPass: String, newPass: String) = false

    /**
     * describe: 注册用户 通过 账号 （手机确认）
     * author 候帅
     * date 2018/8/24 下午2:40
     * @param
     * @return
     */
    fun registerByAccount(registerInfo: RegisterInfoBean): UserDetailBean

    /**
     *  通过账号信息进行获取 对应人员的  账号密码和角色信息
     * @param account 账号信息
     * @return  用户的相关信息
     */
    fun findUserInfo(account: String): UserBean
}