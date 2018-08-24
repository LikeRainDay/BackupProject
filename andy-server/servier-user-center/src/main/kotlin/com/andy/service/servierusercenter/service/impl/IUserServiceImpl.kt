package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.bean.UserDetailBean
import com.andy.service.servierusercenter.bean.LoginEnableBean
import com.andy.service.servierusercenter.bean.RegisterInfoBean
import com.andy.service.servierusercenter.dao.UserDao
import com.andy.service.servierusercenter.entity.UserEntity
import com.andy.service.servierusercenter.enum.SupportLoginFun
import com.andy.service.servierusercenter.exception.NotFoundAccountException
import com.andy.service.servierusercenter.exception.RepeatAccountException
import com.andy.service.servierusercenter.service.IUserService
import com.andy.service.servierusercenter.util.AccountUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert
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


    /**
     * describe: 此方法 需要注意。 由于注册时需要进行 邮箱 或者 电话短信认证
     * author 候帅
     * date 2018/8/24 下午11:21
     */
    @Transactional
    override fun registerByAccount(registerInfo: RegisterInfoBean): UserDetailBean {

        val accountEntity = userDao.checkoutUserInfoByAccount(registerInfo.account)

        Assert.notNull(accountEntity, "Account number repetition")

        val userEntity = UserEntity()

        userEntity.account = registerInfo.account

        val email = registerInfo.email
        val telName = registerInfo.tel
        if (!StringUtils.isEmpty(email)){
            val telEntity = userDao.checkoutUserInfoByTel(registerInfo.tel)

            Assert.notNull(telEntity, "telephone repetition")
        }

        if (!StringUtils.isEmpty(telName)) {
            val emailEntity = userDao.checkoutUserInfoByEmail(registerInfo.email)

            Assert.notNull(emailEntity, "email repetition")
        }


        userEntity.account = registerInfo.account

        // TODO 邮箱验证 或 短信验证
//        userDao.save()
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

    override fun loginEnableByUsername(userId: String): LoginEnableBean {

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


    override fun reviseUserPass(userId: String, oldPass: String, newPass: String): Boolean {

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