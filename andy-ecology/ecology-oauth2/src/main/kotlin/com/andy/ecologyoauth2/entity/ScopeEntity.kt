package com.andy.ecologyoauth2.entity

import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@Table(name = "auth_scope")
class ScopeEntity: AbstractAuditable<Long>() {


    @NotNull
    @Column(name = "value", nullable = false)
    lateinit var value: String

    @OneToMany(mappedBy = "scope", fetch = FetchType.LAZY)
    lateinit var clientDetailsToScopeXrefs: MutableSet<ClientDetailsToScopeXrefEntity>
}