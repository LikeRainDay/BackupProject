package com.andy.ecologygetway.zuul.filter


import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * @author :        侯帅
 * @CreateTime :    2018/4/30/15:17
 * @Descript:       路由层进行拦截
 * @Email:          houshuai0816@126.com
 * 修改人:
 */
@Component
class RouteFilter :ZuulFilter() {
    private var log: Logger = LoggerFactory.getLogger(RouteFilter::class.java)

    override fun run(): Any?{
        val currentContext = RequestContext.getCurrentContext()
        val request = currentContext.request
        log.info("PouteFilter: ${request.method} request to  ${request.requestURL}")
        return null

    }

    override fun shouldFilter(): Boolean {
        return true
    }

    override fun filterType(): String {
        return "route"  // 在路由层前进行相关的拦截
    }


    override fun filterOrder(): Int {
        return 0
    }







}