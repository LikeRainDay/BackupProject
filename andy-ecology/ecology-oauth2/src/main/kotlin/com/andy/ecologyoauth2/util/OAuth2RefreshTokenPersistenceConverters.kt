package com.andy.ecologyoauth2.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.security.oauth2.common.OAuth2RefreshToken
import javax.persistence.AttributeConverter

/**
 * describe: 刷新Token的转化
 * author 候帅
 * date 2018/8/18 下午5:58
 */
class OAuth2RefreshTokenPersistenceConverters: AttributeConverter<OAuth2RefreshToken, String> {

    private val jsonPersistenceConverters = JsonPersistenceConverters<OAuth2RefreshToken>{
        val objectMapper = ObjectMapper()
        val module = SimpleModule()
        module.addSerializer(OAuth2RefreshToken::class.java, OAuth2RefreshTokenJackson2SerializerDeserializer.Companion.OAuth2RefreshTokenJackson2Serializer())
        module.addDeserializer(OAuth2RefreshToken::class.java, OAuth2RefreshTokenJackson2SerializerDeserializer.Companion.OAuth2RefreshJackson2Deserializer())
        objectMapper.registerModule(module)
        return@JsonPersistenceConverters objectMapper
    }


    override fun convertToDatabaseColumn(p0: OAuth2RefreshToken?): String {
        return jsonPersistenceConverters.convertToJson(p0!!)!!
    }

    override fun convertToEntityAttribute(p0: String?): OAuth2RefreshToken {
        return jsonPersistenceConverters.convertFromJson(p0!!, OAuth2RefreshToken::class.java)!!
    }
}