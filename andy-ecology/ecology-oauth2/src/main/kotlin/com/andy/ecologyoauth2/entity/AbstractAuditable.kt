package com.andy.ecologyoauth2.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.AbstractPersistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractAuditable<PK : Serializable>: AbstractPersistable<PK>() {

    @NotNull
    @CreatedBy
    @Size(max = 50)
    @Column(name = "created_by", length = 50, updatable = false, nullable = false)
    lateinit var createBy: String

    @NotNull
    @CreatedDate
    @Column(name = "created_date", updatable = false, nullable = false)
    var createdDate: ZonedDateTime = ZonedDateTime.now()

    @Size(max = 50)
    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    lateinit var lastModifiedBy: String

    @LastModifiedDate
    @Column(name = "last_modified_date")
    var lastModifiedDate: ZonedDateTime = ZonedDateTime.now()
}