package com.andy.ecologyoauth2.service.impl

import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 * FileName: DomainUserDetailsServiceImpl
 * author:   候帅
 * data:     18/08/2018 07:40
 * Description: 自定义用户身份获取
 * History:
 */
@Service
class DomainUserDetailsServiceImpl :UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return User("houshuai", "houshuai", AuthorityUtils.createAuthorityList("Role_Amdin"))
    }
}