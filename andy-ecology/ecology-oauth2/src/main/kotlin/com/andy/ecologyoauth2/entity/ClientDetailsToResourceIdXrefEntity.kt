package com.andy.ecologyoauth2.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull


@Entity
@Table(name = "auth_client_details_resource_xref")
class ClientDetailsToResourceIdXrefEntity: AbstractAuditable<Long>() {

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_details_id")
    lateinit var clientDetails: ClientDetailsEntity

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "resource_id")
    lateinit var resourceId: ResourceIdEntity
}