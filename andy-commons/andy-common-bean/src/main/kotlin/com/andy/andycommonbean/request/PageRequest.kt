package com.andy.andycommonbean.request


/**
 * describe: 分页请求的属性内容
 * author 候帅
 * date 2018/9/20 下午4:49
 */
class PageRequest {

    // 分页大小
    var pageSize: Int = 8

    // 查询的页码
    var currentPage: Int = 1

    // 搜索内容
    var searchValue: String? = null

    // 内容状态
    var statis: Int = -1
}