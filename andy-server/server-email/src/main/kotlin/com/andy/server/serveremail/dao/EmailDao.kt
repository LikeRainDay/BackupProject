package com.andy.server.serveremail.dao

import com.andy.corejpa.AbstractLongRepository
import com.andy.server.serveremail.entity.EmailEntity
import org.springframework.stereotype.Repository

@Repository
interface EmailDao: AbstractLongRepository<EmailEntity> {

}