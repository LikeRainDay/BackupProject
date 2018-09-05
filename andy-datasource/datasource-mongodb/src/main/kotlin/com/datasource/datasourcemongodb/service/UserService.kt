package com.datasource.datasourcemongodb.service

import com.datasource.datasourcemongodb.entity.User


interface UserService {

    fun save(user: User)

    fun findByName(name: String): User
}