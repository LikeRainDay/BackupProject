package com.andy.service.servierusercenter.service.impl

import com.andy.andycommonbean.bean.UserBean
import com.andy.andycommonfeign.EmailFeign
import com.andy.andycommonfeign.SmsFeign
import com.andy.service.servierusercenter.bean.UserDetailBean
import com.andy.service.servierusercenter.bean.LoginEnableBean
import com.andy.service.servierusercenter.bean.RegisterInfoBean
import com.andy.service.servierusercenter.dao.UserDao
import com.andy.service.servierusercenter.entity.UserDetailsEntity
import com.andy.service.servierusercenter.entity.UserEntity
import com.andy.service.servierusercenter.enum.SupportLoginFun
import com.andy.service.servierusercenter.exception.EmailException
import com.andy.service.servierusercenter.exception.NotFoundAccountException
import com.andy.service.servierusercenter.exception.RepeatAccountException
import com.andy.service.servierusercenter.exception.SmsException
import com.andy.service.servierusercenter.service.IUserDetailsService
import com.andy.service.servierusercenter.service.IUserService
import com.andy.service.servierusercenter.util.AccountUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils
import java.util.stream.Collectors

@Service
class IUserServiceImpl: IUserService {


    private val log: Logger = LoggerFactory.getLogger(IUserServiceImpl::class.java)

    @Autowired
    private lateinit var userDao: UserDao

    @Autowired
    private lateinit var smsFeign: SmsFeign

    @Autowired
    private lateinit var emailFeign: EmailFeign

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var iUserDetailsService: IUserDetailsService

    override fun falseDeleteUserById(id: String) {
        userDao.falseDeleteUserById(id)
    }

    override fun findUserInfo(account: String): UserBean {
        var userEntity: UserEntity? = null

        AccountUtil.judgeAccountTypeByListener(account,{
            userDao.checkoutUserInfoByAccount(account).map {
                userEntity = it
            }.orElseThrow { throw NotFoundAccountException("This account does not exist") }
        },{
            userDao.checkoutUserInfoByTel(account).map {
                userEntity = it
            }.orElseThrow { throw NotFoundAccountException("This account does not exist") }
        },{
            userDao.checkoutUserInfoByEmail(account).map {
                userEntity = it
            }.orElseThrow { throw NotFoundAccountException("This account does not exist") }
        },{
            throw NotFoundAccountException.Error(account)
        })
        if (userEntity == null)
            throw NotFoundAccountException("This account does not exist")

        val userBean = UserBean()
        userBean.account =userEntity!!.account
        userBean.password= userEntity!!.password
        userBean.roles = userEntity!!.roles.stream().map {
            return@map it.roleName
        }.collect(Collectors.toSet())
        return userBean
    }

    /**
     * describe: 此方法 需要注意。 由于注册时需要进行 邮箱 或者 电话短信认证
     * author 候帅
     * date 2018/8/24 下午11:21
     */
    @Transactional
    override fun registerByAccount(registerInfo: RegisterInfoBean): UserDetailBean {

        val accountEntity = userDao.checkoutUserInfoByAccount(registerInfo.account)

        if (accountEntity.isPresent)
            throw RepeatAccountException("${registerInfo.account} has registered")

        val userEntity = UserEntity()

        userEntity.account = registerInfo.account

        val email = registerInfo.email
        val tel = registerInfo.tel
        val code = registerInfo.code
        var telNum: String? = null
        var emailNum: String? = null
        if (!StringUtils.isEmpty(tel)){
            val telEntity = userDao.checkoutUserInfoByTel(tel!!)
            if (telEntity.isPresent)
                throw IllegalAccessException("$tel is repetition")
            if (smsFeign.verificationCode(tel, code!!))
                throw SmsException.Error()
            telNum = tel
        }

        if (!StringUtils.isEmpty(email)) {
            val emailEntity = userDao.checkoutUserInfoByEmail(email!!)
            if (emailEntity.isPresent)
                throw IllegalAccessException("$email is repetition")

            if (emailFeign.validEmailCode(email, code!!))
                throw EmailException.Error()
            emailNum = email
        }
        userEntity.account = registerInfo.account
        userEntity.email = email!!
        userEntity.tel = tel!!.toInt()
        userEntity.password = passwordEncoder.encode(registerInfo.password)
        val userDetailsEntity = UserDetailsEntity()
        userDetailsEntity.nickName = registerInfo.nickName
        userEntity.userDetails = iUserDetailsService.save(userDetailsEntity).map {
            return@map it
        }.orElse(null)

        val userInfo = userDao.save(userEntity)

        return UserDetailBean(
                userInfo.id!!,
                0,
                null,
                null,
                null,
                telNum,
                emailNum,
                null,
                null,
                null)
    }

    override fun loginByUsernameAndPass(username: String, pass: String): UserDetailBean {

        var userEntity: UserEntity? = null

        AccountUtil.judgeAccountTypeByListener(username,{
            userEntity = userDao.checkoutUserInfoByAccount(username).get()
        },{
            userEntity = userDao.checkoutUserInfoByTel(username).get()
        },{
            userEntity = userDao.checkoutUserInfoByEmail(username).get()
        },{
            throw NotFoundAccountException.Error(username)
        })
        if (userEntity == null)
            throw NotFoundAccountException("This account does not exist")

        val userDetails = userEntity!!.userDetails

        return UserDetailBean(
                userEntity!!.id!!,
                userDetails.sex,
                userDetails.avator,
                userDetails.nickName,
                userDetails.zipCode,
                userEntity!!.tel.toString(),
                userEntity!!.email,
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


    override fun simpleRegister(account: String, password: String): UserDetailBean {
        val accountEntity = userDao.checkoutUserInfoByAccount(account)

        if (accountEntity.isPresent)
            throw RepeatAccountException("$account has registered")

        val userEntity = UserEntity()

        userEntity.account = account
        userEntity.password = passwordEncoder.encode(password)

        val userInfo = userDao.save(userEntity)


        return UserDetailBean(
                userInfo.id!!,
                0,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null)
    }
}