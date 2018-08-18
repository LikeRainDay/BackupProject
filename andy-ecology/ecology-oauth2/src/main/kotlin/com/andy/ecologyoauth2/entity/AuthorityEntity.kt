package com.andy.ecologyoauth2.entity

import org.hibernate.annotations.ColumnDefault
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@Entity
@Table(name = "authorities")
class AuthorityEntity: AbstractAuditable<Long>() {

    @Size(min = 1, max = 50)
    @Column(name = "authority_name", unique = true, length = 50)
    lateinit var authorityName: String

    @NotNull
    @Column(name = "disabled", nullable = false)
    @ColumnDefault("False")
    var disabled: Boolean = false
}