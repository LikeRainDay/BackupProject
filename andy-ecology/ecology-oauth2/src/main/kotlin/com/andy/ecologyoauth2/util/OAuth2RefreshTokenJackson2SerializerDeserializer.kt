package com.andy.ecologyoauth2.util

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken
import org.springframework.security.oauth2.common.ExpiringOAuth2RefreshToken
import org.springframework.security.oauth2.common.OAuth2RefreshToken
import java.util.*

/**
 * describe: 进行Oauth2RefreshToken 转化为Json数据
 * author 候帅
 * date 2018/8/19 下午6:31
 */
class OAuth2RefreshTokenJackson2SerializerDeserializer {

    private val log: Logger = LoggerFactory.getLogger(OAuth2RefreshTokenJackson2SerializerDeserializer::class.java)

    companion object {

        const val TOKEN_VALUE = "value"

        const val EXPIRATION = "expiration"

        /**
         * describe: 将OAuth2RefreshToken 转化为 Json 数据
         * author 候帅
         * date 2018/8/19 下午6:44
         */
        class OAuth2RefreshTokenJackson2Serializer: StdSerializer<OAuth2RefreshToken>(OAuth2RefreshToken::class.java){

            override fun serialize(value: OAuth2RefreshToken?, gen: JsonGenerator?, provider: SerializerProvider?) {

                gen!!.writeStartObject()
                gen.writeStringField(TOKEN_VALUE, value!!.value)

                if (value is ExpiringOAuth2RefreshToken)
                    gen.writeStringField(EXPIRATION, value.expiration.time.toString())

                gen.writeEndObject()
            }
        }


        /**
         * describe: 将 JSON 转化为对应的OAuth2RefreshToken
         * author 候帅
         * date 2018/8/19 下午6:54
         */
        class OAuth2RefreshJackson2Deserializer: StdDeserializer<OAuth2RefreshToken>(OAuth2RefreshToken::class.java){

            override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): OAuth2RefreshToken {

                var tokenValue: String? = null

                var expiration: Long? = null

                while (p!!.nextToken() != JsonToken.END_OBJECT){
                    val name = p.currentName
                    p.nextToken()
                    if (TOKEN_VALUE == name)
                        tokenValue = p.text
                    else if (EXPIRATION == name){
                        try {
                            expiration = p.longValue
                        } catch (e: JsonParseException) {
                            expiration = p.text.toLong()
                        }
                    }
                }

                return if (expiration != null){
                    DefaultExpiringOAuth2RefreshToken(tokenValue, Date(expiration))
                }else
                    DefaultOAuth2RefreshToken(tokenValue)
            }
        }

    }
}