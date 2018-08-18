package com.andy.ecologyoauth2.util

import org.springframework.security.oauth2.common.OAuth2AccessToken
import javax.persistence.AttributeConverter

/**
 * describe: 进行Toekn转化
 * author 候帅
 * date 2018/8/18 下午3:59
 */
class OAuth2AccessTokenPersistenceConverters: AttributeConverter<OAuth2AccessToken, String> {

    private val jsonPersistenceConverters: JsonPersistenceConverters<OAuth2AccessToken> = JsonPersistenceConverters()

    override fun convertToDatabaseColumn(attribute: OAuth2AccessToken?): String {
        return jsonPersistenceConverters.convertToJson(attribute!!)!!
    }

    override fun convertToEntityAttribute(dbData: String?): OAuth2AccessToken {
        return jsonPersistenceConverters.convertFromJson(dbData!!, OAuth2AccessToken::class.java)!!
    }
}