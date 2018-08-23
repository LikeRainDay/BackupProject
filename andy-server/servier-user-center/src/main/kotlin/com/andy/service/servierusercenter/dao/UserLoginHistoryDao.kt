package com.andy.service.servierusercenter.dao

import com.andy.service.servierusercenter.entity.UserLoginHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * FileName: UserLoginHistoryDao
 * author:   候帅
 * data:     23/08/2018 22:05
 * Description: TODO
 * History:
 */
interface UserLoginHistoryDao: JpaRepository<UserLoginHistoryEntity, Long> {
}