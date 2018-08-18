package com.andy.ecologyoauth2.dao

import com.andy.ecologyoauth2.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface RoleRepository: JpaRepository<RoleEntity, Long> {

    fun findOneByName(roleName: String): Optional<RoleEntity>

}