package com.andy.corejpa

import org.springframework.data.jpa.repository.JpaRepository

/**
 * describe: 用于JPA long 型的主键
 * author 候帅
 * date 2018/8/24 上午9:48
 */
interface AbstractLongRepository<T>: JpaRepository<T, Long>