package com.andy.ecologyoauth2.dao

import com.andy.ecologyoauth2.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface UserRepository: JpaRepository<UserEntity, Long> {

    fun findOneByUsername(username: String): Optional<UserEntity>
}