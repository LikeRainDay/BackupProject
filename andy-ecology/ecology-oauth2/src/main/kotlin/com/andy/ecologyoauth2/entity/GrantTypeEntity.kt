package com.andy.ecologyoauth2.entity

import javax.persistence.*


@Entity
@Table(name = "grant_type")
class GrantTypeEntity: AbstractAuditable<Long>() {

    @Column(name = "value", nullable = false)
    lateinit var value: String

    @OneToMany(mappedBy = "grantType", fetch = FetchType.LAZY)
    lateinit var clientDetailToAuthorizedGrantTypeXrefEntity: Set<ClientDetailsToAuthorizedGrantTypeXrefEntity>
}