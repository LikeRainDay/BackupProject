package com.andy.corejpa.utils

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * describe: 分页管理
 * author 候帅
 * date 2018/9/20 下午5:22
 */
class PageInfo : Pageable {


    constructor(page: Int, size: Int) {
        if (page < 1)
            throw IllegalArgumentException("Page orgsIndex must not be less than 1!")
        if (size < 1)
            throw IllegalArgumentException("Page size must not be less than one!")
        this.page = page
        this.size = size
    }

    constructor(page: Int, size: Int, sort: Sort) : this(page, size) {
        this.mSort = sort
    }

    constructor(page: Int, size: Int, direction: Sort.Direction, properties: List<String>) : this(page, size, Sort(direction, properties))

    private var mSort: Sort? = null

    private var page: Int = 1

    private var size: Int = 8

    override fun getPageNumber(): Int = page

    override fun hasPrevious(): Boolean = page > 1

    override fun getSort(): Sort = mSort!!

    override fun next(): Pageable = PageInfo(pageNumber + 1, pageSize, sort)

    override fun getPageSize(): Int = size

    override fun getOffset(): Long = ((page - 1) * size).toLong()

    override fun first(): Pageable = PageInfo(1, pageSize, sort)

    override fun previousOrFirst(): Pageable = if (hasPrevious()) previous() else first()


    private fun previous(): Pageable {
        return if (pageNumber == 1) this else PageRequest.of(pageNumber - 1, pageSize, sort)
    }
}