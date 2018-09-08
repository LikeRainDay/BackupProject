package com.andy.corejpa

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.AbstractPersistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.ZonedDateTime
import java.util.*
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
abstract class AbstractOrganizeAuditable: AbstractUuidPersistable() {

    @NotNull
    @CreatedBy
    @Size(max = 50)
    @Column(name = "created_by", length = 50, updatable = false, nullable = false)
    var createBy: String = UUID.randomUUID().toString()

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

    // 唯一ID
    @NotNull
    @Column(name = "unique_id", nullable = true)
    var uniqueId: Long = 0

    // 机构的索引
    @NotNull
    @Column(name = "organize_index", nullable = true)
    lateinit var organizeIndex: String

    // 机构登记
    @Size(max = 30)
    @Column(name = "organize_level", nullable = true, length = 30)
    var organizeLevel: Long = 0

}