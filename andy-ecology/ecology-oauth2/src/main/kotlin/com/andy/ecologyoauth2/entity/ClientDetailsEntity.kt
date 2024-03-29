package com.andy.ecologyoauth2.entity

import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@Table(name = "auth_client_details")
class ClientDetailsEntity: AbstractAuditable<Long>() {

    @NotNull
    @Column(name = "client_id", unique = true, nullable = false, length = 200)
    lateinit var clientId: String

    @NotNull
    @Column(name = "client_secret", nullable = false)
    lateinit var clientSecret: String

    @Column(name = "access_token_validity_seconds")
    var accessTokenValiditySeconds: Int = 0

    @Column(name = "refresh_toekn_validity_seconds")
    var refreshTokenValiditySeconds: Int = 0


    @OneToMany(mappedBy = "clientDetails", fetch = FetchType.EAGER, orphanRemoval = true, cascade = [CascadeType.ALL])
    lateinit var authorizedGrantTypeXrefs: MutableSet<ClientDetailsToAuthorizedGrantTypeXrefEntity>


    @OneToMany(mappedBy = "clientDetails", fetch = FetchType.EAGER, orphanRemoval = true, cascade = [CascadeType.ALL])
    lateinit var scopeXrefs: MutableSet<ClientDetailsToScopeXrefEntity>


    @OneToMany(mappedBy = "clientDetails", fetch = FetchType.EAGER, orphanRemoval = true, cascade = [CascadeType.ALL])
    lateinit var resourceIdXrefs: MutableSet<ClientDetailsToResourceIdXrefEntity>


    @OneToMany(mappedBy = "clientDetails", fetch = FetchType.EAGER, orphanRemoval = true, cascade = [CascadeType.ALL])
    lateinit var redirectUris: MutableSet<RedirectUrlEntity>


    @OneToOne(mappedBy = "clientDetail", fetch = FetchType.EAGER, orphanRemoval = true, cascade = [CascadeType.ALL])
    lateinit var clientLimit: ClientDetailsLimitEntity

}