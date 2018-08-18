package com.andy.ecologyoauth2.dao

import com.andy.ecologyoauth2.entity.ClientDetailsEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface ClientDetailsRepository: JpaRepository<ClientDetailsEntity, Long> {

    fun findOneByClientId(clientId: String): Optional<ClientDetailsEntity>

}