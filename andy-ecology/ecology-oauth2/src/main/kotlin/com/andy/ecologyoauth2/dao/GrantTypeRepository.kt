package com.andy.ecologyoauth2.dao

import com.andy.ecologyoauth2.entity.AccessTokenEntity
import com.andy.ecologyoauth2.entity.GrantTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface GrantTypeRepository: JpaRepository<GrantTypeEntity, Long> {

    fun findOneByValue(value: String): Optional<GrantTypeEntity>
}