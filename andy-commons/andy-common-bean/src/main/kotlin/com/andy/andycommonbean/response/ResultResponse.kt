package com.andy.andycommonbean.response



/**
 * describe: 响应结果
 * author 候帅
 * date 2018/8/30 下午3:39
 */
class ResultResponse<T>: BaseResponse() {


    /**
     * describe: 内容信息
     * author 候帅
     * date 2018/8/25 下午12:06
     */
    var result: T? = null


   companion object {

       fun<T> Success(result: T): ResultResponse<T> {
           val resultResponse = ResultResponse<T>()
           resultResponse.result = result
           resultResponse.reason = "请求成功"
           return resultResponse
       }

   }

}