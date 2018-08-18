package com.andy.ecologyoauth2.dao

import com.andy.ecologyoauth2.entity.RedirectUrlEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface RedirectUrlRepository: JpaRepository<RedirectUrlEntity, Long> {

    fun findOneByValue(value: String): Optional<RedirectUrlEntity>

}