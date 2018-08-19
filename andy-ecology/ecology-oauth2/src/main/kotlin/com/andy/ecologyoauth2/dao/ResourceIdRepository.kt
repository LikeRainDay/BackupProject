package com.andy.ecologyoauth2.dao

import com.andy.ecologyoauth2.entity.ResourceIdEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface ResourceIdRepository: JpaRepository<ResourceIdEntity, Long> {

    fun findOneByValue(value: String): Optional<ResourceIdEntity>
}