package com.andy.ecologyoauth2.dao

import com.andy.ecologyoauth2.entity.ScopeEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface ScopeRepository: JpaRepository<ScopeEntity, Long> {

    fun findOneByValue(value: String): Optional<ScopeEntity>

}