package com.andy.ecologyoauth2.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

/**
 * FileName: UserController
 * author:   候帅
 * data:     18/08/2018 07:39
 * Description: 用户身份获取接口
 * History:
 */
@Api(description = "用户身份信息获取")
@RestController
class UserController {

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/user")
    fun user(user: Principal): Principal {
        return user
    }
//
//    @ApiOperation(value = "通过Access_token获取用户信息")
//    @ApiImplicitParam(name = "token",value = "AccessToken", dataType = "String", paramType = "query", required = true)
//    @GetMapping(value = ["/oauth/check_token"])
//    fun checkToken(@RequestParam(value = "token", required = true) token: String?): Map<String, *> {
//        if (token == null)
//            throw IllegalAccessException("The token field is required")
//
//    }
}