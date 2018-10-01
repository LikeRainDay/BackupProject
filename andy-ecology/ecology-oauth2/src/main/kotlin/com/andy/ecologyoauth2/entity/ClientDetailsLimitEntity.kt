package com.andy.ecologyoauth2.entity

import javax.persistence.*


@Entity
@Table(name = "auth_client_detail_limit")
class ClientDetailsLimitEntity: AbstractAuditable<Long>() {

    @Column(name = "intervalInMills")
    var intervalInMills: Long = 0L

    @Column(name = "limits")
    var limits: Long = 0L

    @OneToOne
    @JoinColumn(name = "client_id")
    lateinit var clientDetail: ClientDetailsEntity
}