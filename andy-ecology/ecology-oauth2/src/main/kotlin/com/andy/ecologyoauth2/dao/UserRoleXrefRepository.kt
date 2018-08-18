package com.andy.ecologyoauth2.dao

import com.andy.ecologyoauth2.entity.UserRoleXrefEntity
import org.springframework.data.jpa.repository.JpaRepository


interface UserRoleXrefRepository: JpaRepository<UserRoleXrefEntity, Long> {


}