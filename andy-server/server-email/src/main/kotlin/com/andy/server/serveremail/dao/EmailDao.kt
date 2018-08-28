package com.andy.server.serveremail.dao

import com.andy.corejpa.AbstractLongRepository
import com.andy.server.serveremail.entity.EmailEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmailDao: AbstractLongRepository<EmailEntity> {

    @Query("SELECT em FROM EmailEntity em WHERE em.receiver = ?1 ORDER BY em.createdDate desc LIMIT 1")
    fun findLastByEmail(email: String): Optional<EmailEntity>

}