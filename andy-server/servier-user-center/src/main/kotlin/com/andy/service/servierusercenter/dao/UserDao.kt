package com.andy.service.servierusercenter.dao;

import com.andy.corejpa.AbstractStringRepository
import com.andy.service.servierusercenter.entity.UserEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * FileName: UserDao
 * author:   候帅
 * data:     23/08/2018 22:04
 * Description: 进行用户操作的dao
 * History:
 */
interface UserDao: AbstractStringRepository<UserEntity> {
    
    /**
     * describe: 伪删除对应ID的用户
     * author 候帅  
     * date 2018/8/24 上午9:54  
     * @param id 用户的唯一ID
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE UserEntity u SET u.status = 3 WHERE u.id = ?1")
    fun falseDeleteUserById(id: String)
    
    /**
     * describe: 查询出对应的账号信息内容 通过 账号
     * author 候帅  
     * date 2018/8/24 上午10:39  
     * @param account 用户账号
     * @return  用户信息
     */  
    @Query(value = "SELECT u FROM UserEntity u WHERE u.status = 0 AND u.account = ?1")
    fun checkoutUserInfoByAccount(account: String): Optional<UserEntity>


    /**
     * describe: 查询出对应的账号信息内容 通过 邮箱
     * author 候帅  
     * date 2018/8/24 上午10:42  
     * @param email 用户绑定的邮箱
     * @return 用户信息
     */
    @Query(value = "SELECT u FROM UserEntity u WHERE u.status = 0 AND u.email = ?1")
    fun checkoutUserInfoByEmail(email: String): Optional<UserEntity>
    
    
    /**
     * describ: 查询出对应的账号信息内容 通过 电话
     * author 候帅  
     * date 2018/8/24 上午10:51  
     * @param 
     * @return   
     */
    @Query(value = "SELECT u FROM UserEntity u WHERE u.status = 0 AND u.tel = ?1")
    fun checkoutUserInfoByTel(tel: String): Optional<UserEntity>


    /**
     * describe: 修改对应账号的密码
     * author 候帅
     * date 2018/8/24 下午2:37
     */
    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.password = ?2 WHERE u.id = ?1")
    fun modiftyPassByUserId(userId: String, newPass: String)



}
