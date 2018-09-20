package com.andy.corejpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor


/**
 * describe: 用于JPA 的字符类型的主键
 * author 候帅
 * date 2018/8/24 上午9:48
 */
interface AbstractStringRepository<T> : JpaRepository<T, String>, JpaSpecificationExecutor<T>