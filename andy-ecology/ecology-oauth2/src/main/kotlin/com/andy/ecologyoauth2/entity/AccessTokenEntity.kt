package com.andy.ecologyoauth2.entity

import com.andy.ecologyoauth2.util.OAuth2AccessTokenPersistenceConverters
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "access_token")
class AccessTokenEntity: AbstractAuditable<Long>(){

    // 对应的Token Id
    @NotNull
    @Column(name = "token_id", nullable = false, unique = true, length = 50)
    lateinit var tokenId: String

    // 序列化AccessToken 的json
    @NotNull
    @Convert(converter = OAuth2AccessTokenPersistenceConverters::class)
    @Column(name = "serialized_token", nullable = false)
    lateinit var token: OAuth2AccessToken

    @NotNull
    @Column(name = "authentication_id", nullable = false, length = 32)
    lateinit var authenticationId: String

    @Size(max =50)
    @Column(name = "user_name", length = 50)
    lateinit var userName: String

    @Size(max = 200)
    @Column(name = "client_id", length = 200)
    lateinit var clientId: String

    @NotNull
    @Lob
    @Column(name = "serialized_authentication", nullable = false)
    lateinit var authentication: OAuth2Authentication

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "refresh_token_id", nullable = true)
    lateinit var refreshToken: RefreshTokenEntity

}