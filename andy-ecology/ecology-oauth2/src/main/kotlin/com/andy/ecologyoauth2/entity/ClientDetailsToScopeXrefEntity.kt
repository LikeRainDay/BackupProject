package com.andy.ecologyoauth2.entity

import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@Table(name = "client_details_scope_xref")
class ClientDetailsToScopeXrefEntity: AbstractAuditable<Long>() {

    @NotNull
    @Column(name = "auto_approve", nullable = false)
    var autoApprove: Boolean = false

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_details_id")
    lateinit var clientDetails: ClientDetailsEntity

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "scope_id")
    lateinit var scope: ScopeEntity
}