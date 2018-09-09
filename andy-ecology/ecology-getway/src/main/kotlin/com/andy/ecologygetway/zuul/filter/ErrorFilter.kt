package com.andy.ecologygetway.zuul.filter


import org.springframework.stereotype.Component
import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author :        侯帅
 * @CreateTime :    2018/4/30/15:18
 * @Descript:       错误码拦截
 * @Email:          houshuai0816@126.com
 * 修改人:
 */
@Component
class ErrorFilter :ZuulFilter() {
    private var log: Logger = LoggerFactory.getLogger(ErrorFilter::class.java)

    override fun run(): Any ?{
        val currentContext = RequestContext.getCurrentContext()
        val request = currentContext.response
        log.info("ErrorFilter: response status is ${request.status}")
        return null
    }

    override fun shouldFilter(): Boolean {
        return true
    }

    override fun filterType(): String {
        return "error"  // 在路由层前进行相关的拦截
    }


    override fun filterOrder(): Int {
        return 0
    }







}