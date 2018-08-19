package com.andy.ecologyoauth2.dao

import com.andy.ecologyoauth2.entity.RoleEntity
import com.andy.ecologyoauth2.entity.UserEntity
import com.andy.ecologyoauth2.entity.UserRoleXrefEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*


interface UserRoleXrefRepository: JpaRepository<UserRoleXrefEntity, Long> {


    @Query("select ur from  UserRoleXrefEntity ur where ur.role =?1 and ur.user = ?2")
    fun findIsPeplace(roleId: RoleEntity, userId: UserEntity): Optional<UserRoleXrefEntity>
    
}