package com.andy.ecologyoauth2.entity

import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@Table(name = "auth_resource")
class ResourceIdEntity: AbstractAuditable<Long>() {



    @NotNull
    @Column(name = "value", nullable = false)
    lateinit var value: String

    @OneToMany(mappedBy = "resourceId", fetch = FetchType.LAZY)
    lateinit var clientDetailsToResourceIdXrefs: Set<ClientDetailsToResourceIdXrefEntity>
}