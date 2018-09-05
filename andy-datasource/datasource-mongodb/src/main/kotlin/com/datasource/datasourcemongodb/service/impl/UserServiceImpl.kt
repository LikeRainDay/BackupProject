package com.datasource.datasourcemongodb.service.impl

import com.datasource.datasourcemongodb.entity.User
import com.datasource.datasourcemongodb.respoisty.UserRepository
import com.datasource.datasourcemongodb.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl: UserService {

    private val log: Logger = LoggerFactory.getLogger(UserServiceImpl::class.java)


    @Autowired
    private lateinit var  userRepository: UserRepository

    override fun save(user: User) {
        userRepository.save(user)
    }

    override fun findByName(name: String): User {
        return userRepository.findByName(name)
    }


}