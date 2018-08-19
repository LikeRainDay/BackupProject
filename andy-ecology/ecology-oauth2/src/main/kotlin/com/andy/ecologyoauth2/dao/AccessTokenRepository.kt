package com.andy.ecologyoauth2.dao

import com.andy.ecologyoauth2.entity.AccessTokenEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface AccessTokenRepository: JpaRepository<AccessTokenEntity, Long> {


    fun findOneByTokenId(tokenId: String): Optional<AccessTokenEntity>

    fun findOneByAuthenticationId (authenticationId: String): Optional<AccessTokenEntity>

    fun deleteByRefreshTokenTokenId(refreshTokenId: String)

    fun deleteByTokenId(tokenId: String)

    fun findAllByClientIdAndUserName(clientId: String, userName: String): List<AccessTokenEntity>

    fun findAllByClientId(clientId: String): List<AccessTokenEntity>

}