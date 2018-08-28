package com.andy.server.serveremail.dao

import com.andy.corejpa.AbstractLongRepository
import com.andy.server.serveremail.entity.EmailEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmailDao: AbstractLongRepository<EmailEntity> {

    fun findFirstByReceiverOrderByCreatedDate(email: String): Optional<EmailEntity>

}