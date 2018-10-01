package com.andy.ecologyoauth2.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull


@Entity
@Table(name = "auth_client_details_grant_type_xref")
class ClientDetailsToAuthorizedGrantTypeXrefEntity: AbstractAuditable<Long>() {

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_details_id")
    lateinit var clientDetails: ClientDetailsEntity

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "grant_type_id")
    lateinit var grantType: GrantTypeEntity
}