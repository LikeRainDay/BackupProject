package com.andy.andycommonbean.controller

import com.andy.andycommonbean.exception.RepeatParamException
import com.andy.andycommonbean.response.BaseResponse
import com.andy.andycommonbean.response.ResultResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * describe: 公用处理Controller异常的返回
 * author 候帅
 * date 2018/9/20 下午4:33
 */
@ControllerAdvice
open class BaseController {

    /**
     * describe: 非法的元素异常
     * author 候帅
     * date 2018/9/20 下午4:35
     */
    @ExceptionHandler(IllegalAccessException::class)
    fun illegalAccessException(error: IllegalAccessException): BaseResponse {
        return ResultResponse.error(error.message!!)
    }


    /**
     * describe: 数据库内容重复
     * author 候帅
     * date 2018/9/20 下午4:35
     */
    @ExceptionHandler(RepeatParamException::class)
    fun dbRepeateException(error: RepeatParamException): BaseResponse {
        return ResultResponse.error(error.message!!)
    }

    /**
     * describe: 所有异常内容的捕获处理
     * author 候帅
     * date 2018/9/21 上午9:49
     */
    @ExceptionHandler(java.lang.Exception::class)
    fun allExceptionHandler(exception: Exception): BaseResponse {
        return ResultResponse.error(exception.message!!)
    }
}