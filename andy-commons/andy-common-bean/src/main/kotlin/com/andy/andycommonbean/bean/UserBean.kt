package com.andy.andycommonbean.bean

import java.io.Serializable

/**
 * FileName: UserBean
 * author:   候帅
 * data:     03/09/2018 21:30
 * Description: 用户实体Bean
 * History:
 */
class UserBean: Serializable {

    // 账号
    lateinit var account: String

    // 密码
    lateinit var password: String

    // 角色信息
    lateinit var roles: Set<String>
}