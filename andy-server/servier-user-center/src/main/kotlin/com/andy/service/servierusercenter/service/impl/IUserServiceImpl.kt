package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.bean.UserDetailBean
import com.andy.service.servierusercenter.bean.LoginEnableBean
import com.andy.service.servierusercenter.dao.UserDao
import com.andy.service.servierusercenter.entity.UserEntity
import com.andy.service.servierusercenter.enum.SupportLoginFun
import com.andy.service.servierusercenter.exception.NotFoundAccountException
import com.andy.service.servierusercenter.service.IUserService
import com.andy.service.servierusercenter.util.AccountUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
class IUserServiceImpl: IUserService {



    private val log: Logger = LoggerFactory.getLogger(IUserServiceImpl::class.java)

    @Autowired
    private lateinit var userDao: UserDao

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    override fun falseDeleteUserById(id: String) {
        userDao.falseDeleteUserById(id)
    }

    override fun registerByAccount(account: String, pass: String, tel: String, email: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loginByUsernameAndPass(username: String, pass: String): UserDetailBean {

        var userEntity: UserEntity? = null

        AccountUtil.judgeAccountTypeByListener(username,{
            userEntity = userDao.checkoutUserInfoByAccount(username)
        },{
            userEntity = userDao.checkoutUserInfoByTel(username)
        },{
            userEntity = userDao.checkoutUserInfoByEmail(username)
        },{
            throw NotFoundAccountException.Error(username)
        })
        if (userEntity == null)
            throw NotFoundAccountException("This account does not exist")

        val userDetails = userEntity!!.userDetails

        return UserDetailBean(
                userEntity?.id!!,
                userDetails.sex,
                userDetails.avator,
                userDetails.nickName,
                userDetails.zipCode,
                userEntity?.tel!!,
                userEntity?.email!!,
                userDetails.idCard,
                userDetails.summary,
                userDetails.adress
        )
    }

    override fun loginEableByUsername(userId: String): LoginEnableBean {

        val user = userDao.findById(userId)

       return user.map {

           val temp = it

           val loginEnable = LoginEnableBean()

           it.userAuths.forEach {
               when(it.identityType){
                   SupportLoginFun.ACCOUNT.value -> loginEnable.account = StringUtils.isEmpty(temp.account)
                   SupportLoginFun.EMAIL.value -> loginEnable.email = StringUtils.isEmpty(temp.email)
                   SupportLoginFun.TEL.value -> loginEnable.tel = StringUtils.isEmpty(temp.tel)
                   SupportLoginFun.QQ.value -> loginEnable.QQ = StringUtils.isEmpty(it.identifier)
                   SupportLoginFun.WX.value -> loginEnable.WX = StringUtils.isEmpty(it.identifier)
                   SupportLoginFun.WB.value -> loginEnable.WB = StringUtils.isEmpty(it.identifier)
                   SupportLoginFun.QR360.value -> loginEnable.QR360 = StringUtils.isEmpty(it.identifier)
                   SupportLoginFun.WY.value -> loginEnable.WY = StringUtils.isEmpty(it.identifier)
               }
           }
           return@map loginEnable
       }.orElseGet {
           return@orElseGet null
       }
    }


    override fun modiftyUserPass(userId: String, oldPass: String, newPass: String): Boolean {

        val user = userDao.findById(userId)
        return user.map {
            val matches = passwordEncoder.matches(oldPass, it.password)
            if (matches){
                userDao.modiftyPassByUserId(userId, passwordEncoder.encode(newPass))
            }else{
                throw RuntimeException("The original password is incorrect")
            }
            return@map true
        }.orElse(false)
    }
}