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

       /**
        * describe: 请求成功 返回对应类型数据
        * author 候帅
        * date 2018/9/5 下午10:39
        * @param result 返回对应结果数据
        * @return 结果响应数据
        */
       fun<T> success(result: T): ResultResponse<T> {
           val resultResponse = ResultResponse<T>()
           resultResponse.result = result
           resultResponse.reason = "请求成功"
           return resultResponse
       }

       /**
        * describe: 请求成功，返回对应数据内容
        * author 候帅
        * date 2018/9/5 下午10:39
        */
       fun success(): ResultResponse<String> {
           val resultResponse = ResultResponse<String>()
           resultResponse.result = null
           resultResponse.reason = "请求成功"
           return resultResponse
       }

       /**
        * describe: 请求失败 返回对应的失败内容
        * author 候帅
        * date 2018/9/5 下午10:40
        */
       fun error(): ResultResponse<String> {
           val resultResponse = ResultResponse<String>()
           resultResponse.result = null
           resultResponse.reason = "请求失败"
           resultResponse.code = 1000
           return resultResponse
       }

       /**
        * describe: 请求失败 返回对应的失败内容
        * author 候帅
        * date 2018/9/5 下午10:40
        */
       fun error(message: String): ResultResponse<String> {
           val resultResponse = ResultResponse<String>()
           resultResponse.result = message
           resultResponse.reason = "请求失败"
           resultResponse.code = 1000
           return resultResponse
       }
   }

}