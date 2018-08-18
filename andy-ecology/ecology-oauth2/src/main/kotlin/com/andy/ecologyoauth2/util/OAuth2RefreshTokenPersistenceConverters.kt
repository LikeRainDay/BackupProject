package com.andy.ecologyoauth2.util

import org.springframework.security.oauth2.common.OAuth2RefreshToken
import javax.persistence.AttributeConverter

/**
 * describe: 刷新Token的转化
 * author 候帅
 * date 2018/8/18 下午5:58
 */
class OAuth2RefreshTokenPersistenceConverters: AttributeConverter<OAuth2RefreshToken, String> {

    private val jsonPersistenceConverters = JsonPersistenceConverters<OAuth2RefreshToken>()


    override fun convertToDatabaseColumn(p0: OAuth2RefreshToken?): String {
        return jsonPersistenceConverters.convertToJson(p0!!)!!
    }

    override fun convertToEntityAttribute(p0: String?): OAuth2RefreshToken {
        return jsonPersistenceConverters.convertFromJson(p0!!, OAuth2RefreshToken::class.java)!!
    }
}