package com.andy.ecologyoauth2.service

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 * FileName: DomainUserDetailsService
 * author:   候帅
 * data:     18/08/2018 07:40
 * Description: 自定义用户身份获取
 * History:
 */
@Service
class DomainUserDetailsService :UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val simpleGrantedAuthority = SimpleGrantedAuthority("Role")
        val mutableListOf = mutableListOf<SimpleGrantedAuthority>()
        mutableListOf.add(simpleGrantedAuthority)
        return User("houshuai", "houshuai", mutableListOf)

    }
}