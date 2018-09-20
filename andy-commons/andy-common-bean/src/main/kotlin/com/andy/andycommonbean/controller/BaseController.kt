package com.andy.andycommonbean.controller

import com.andy.andycommonbean.response.BaseResponse
import com.andy.andycommonbean.response.ResultResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

/**
 * describe: 公用处理Controller异常的返回
 * author 候帅
 * date 2018/9/20 下午4:33
 */
open class BaseController {

    /**
     * describe: 非法的元素异常
     * author 候帅
     * date 2018/9/20 下午4:35
     */
    @ResponseBody
    @ExceptionHandler(IllegalAccessException::class)
    fun illegalAccessException(): BaseResponse {
        return ResultResponse.error("error params")
    }


}