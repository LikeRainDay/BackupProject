package com.datasource.datasourcemongodb.service.impl

import com.datasource.datasourcemongodb.entity.User
import com.datasource.datasourcemongodb.service.UserService
import jdk.nashorn.internal.runtime.regexp.joni.Config.log
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class UserServiceImplTest{

    private var log = LoggerFactory.getLogger(UserServiceImplTest::class.java)

    @Autowired
    private lateinit var userService: UserService

    @Test
    fun test(): Unit {

        val user = User()
        user.age = 10
        user.id  = 12
        user.name = "你好"

        userService.save(user)

        val findByName = userService.findByName(user.name)
        log.info("当前 获取到的用户信息为：$findByName")
    }

}