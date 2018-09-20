package com.andy.server.serveremail.dao

import com.andy.server.serveremail.entity.EmailEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*

interface EmailDao : JpaRepository<EmailEntity, Long>, JpaSpecificationExecutor<EmailEntity> {

    fun findFirstByReceiverOrderByCreatedDate(email: String): Optional<EmailEntity>

}