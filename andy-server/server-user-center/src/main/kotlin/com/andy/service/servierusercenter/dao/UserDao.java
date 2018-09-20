package com.andy.service.servierusercenter.dao;


import com.andy.service.servierusercenter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 作者：候帅
 * 时间：2018:09:20-15:52
 * 描述： 用户操作的dao
 */
public interface UserDao extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

    /**
     * describe: 伪删除对应ID的用户
     * author 候帅
     * date 2018/9/20 下午3:54
     *
     * @param id 用户的唯一ID
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE UserEntity u SET u.status = 3 WHERE u.id = ?1")
    void falseDeleteUserById(String id);


    /**
     * describe: 查询出对应的账号信息内容 通过 账号
     * author 候帅
     * date 2018/9/20 下午3:55
     *
     * @param account 用户账号
     * @return 用户信息
     */
    @Query(value = "SELECT u FROM UserEntity u WHERE u.status = 0 AND u.account = ?1")
    Optional<UserEntity> checkoutUserInfoByAccount(String account);

    /**
     * describe: 查询出对应的账号信息内容 通过 邮箱
     * author 候帅
     * date 2018/8/24 上午10:42
     *
     * @param email 用户绑定的邮箱
     * @return 用户信息
     */
    @Query(value = "SELECT u FROM UserEntity u WHERE u.status = 0 AND u.email = ?1")
    Optional<UserEntity> checkoutUserInfoByEmail(String email);

    /**
     * describ: 查询出对应的账号信息内容 通过 电话
     * author 候帅
     * date 2018/8/24 上午10:51
     *
     * @param
     * @return
     */
    @Query(value = "SELECT u FROM UserEntity u WHERE u.status = 0 AND u.tel = ?1")
    Optional<UserEntity> checkoutUserInfoByTel(String tel);

    /**
     * describe: 修改对应账号的密码
     * author 候帅
     * date 2018/8/24 下午2:37
     */
    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.password = ?2 WHERE u.id = ?1")
    void modiftyPassByUserId(String userId, String newPass);

}
