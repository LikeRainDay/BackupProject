package com.andy.service.servierusercenter.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.AbstractPersistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractEntity<PK : Serializable>: AbstractPersistable<PK>()  {

    @NotNull
    @CreatedDate
    @Column(name = "created_date", updatable = false, nullable = false)
    var createdDate: ZonedDateTime = ZonedDateTime.now()


    @Size(max = 50)
    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    lateinit var lastModifiedBy: String
}