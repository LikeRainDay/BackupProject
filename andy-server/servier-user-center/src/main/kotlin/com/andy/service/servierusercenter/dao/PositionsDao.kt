package com.andy.service.servierusercenter.dao;

import com.andy.service.servierusercenter.entity.PositionsEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * FileName: UserDao
 * author:   候帅
 * data:     23/08/2018 22:04
 * Description: TODO
 * History:
 */
interface PositionsDao: JpaRepository<PositionsEntity, Long> {
}
