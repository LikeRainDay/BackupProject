package com.andy.ecologyoauth2.entity

import com.andy.ecologyoauth2.util.OAuth2RefreshTokenPersistenceConverters
import org.springframework.security.oauth2.common.OAuth2RefreshToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "refresh_token")
class RefreshTokenEntity: AbstractAuditable<Long>() {

    @NotNull
    @Column(name = "token_id", nullable = false, unique = true, length = 36)
    lateinit var tokenId: String

    @NotNull
    @Convert(converter = OAuth2RefreshTokenPersistenceConverters::class)
    @Column(name = "serialized_token", nullable = false)
    lateinit var token: OAuth2RefreshToken

    @NotNull
    @Lob
    @Column(name = "serialized_authentication", nullable = false)
    lateinit var authentication: OAuth2Authentication

    @OneToMany(mappedBy = "refreshToken", fetch = FetchType.LAZY)
    lateinit var accessTokens: Set<AccessTokenEntity>
}