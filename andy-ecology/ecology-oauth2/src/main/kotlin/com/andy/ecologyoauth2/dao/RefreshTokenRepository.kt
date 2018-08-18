package com.andy.ecologyoauth2.dao

import com.andy.ecologyoauth2.entity.RefreshTokenEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface RefreshTokenRepository: JpaRepository<RefreshTokenEntity, Long> {

    fun findOneByTokenId(tokenId: String): Optional<RefreshTokenEntity>

    fun deleteByTokenId(tokenId: String)
}