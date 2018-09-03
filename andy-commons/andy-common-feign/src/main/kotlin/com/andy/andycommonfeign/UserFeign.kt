package com.andy.andycommonfeign

import com.andy.andycommonbean.bean.UserBean
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

/**
 * FileName: UserFeign
 * author:   候帅
 * data:     03/09/2018 21:27
 * Description: TODO
 * History:
 */
@FeignClient(name = "service-account-center")
@RequestMapping(value = ["/user"])
interface UserFeign {

    @RequestMapping(value = ["/find"])
    fun findUserInfo(@RequestParam(value = "account") accout: String): Optional<UserBean>
}