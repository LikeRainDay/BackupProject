package com.andy.corejpa

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.AbstractPersistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * describe:
 * author 候帅
 * date 2018/8/19 上午11:19
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractIdAuditable: AbstractPersistable<Long>() {


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