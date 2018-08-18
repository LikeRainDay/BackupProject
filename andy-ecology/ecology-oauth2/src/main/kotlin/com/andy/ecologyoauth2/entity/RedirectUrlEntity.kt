package com.andy.ecologyoauth2.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "redirect_url")
class RedirectUrlEntity: AbstractAuditable<Long>() {

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_details_id", nullable = false)
    lateinit var clientDetails: ClientDetailsEntity

    @NotNull
    @Column(name = "value", nullable = false)
    lateinit var value: String

}
