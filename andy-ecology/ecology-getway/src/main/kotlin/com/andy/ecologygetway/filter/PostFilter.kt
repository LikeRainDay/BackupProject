package com.andy.ecologygetway.filter


import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
/**
 * @author :        侯帅
 * @CreateTime :    2018/4/30/15:14
 * @Descript:       进行Post 过滤
 * @Email:          houshuai0816@126.com
 * 修改人:
 */
@Component
class PostFilter :ZuulFilter() {
    private var log: Logger = LoggerFactory.getLogger(PostFilter::class.java)


    override fun run(): Any? {
        val currentContext = RequestContext.getCurrentContext()
        val request = currentContext.response
        log.info("PostFilter: response's content type is ${request.status}")
        return null
    }

    override fun shouldFilter(): Boolean {
        return true
    }

    override fun filterType(): String {
        return "post"  // 在路由层前进行相关的拦截
    }


    override fun filterOrder(): Int {
        return 0
    }







}