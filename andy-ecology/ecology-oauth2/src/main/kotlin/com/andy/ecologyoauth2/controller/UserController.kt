package com.andy.ecologyoauth2.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

/**
 * FileName: UserController
 * author:   候帅
 * data:     18/08/2018 07:39
 * Description: 用户身份获取接口
 * History:
 */
@RestController
class UserController {

    @GetMapping("/user")
    fun user(user: Principal): Principal {
        return user
    }

}