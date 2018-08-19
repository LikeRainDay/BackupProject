package com.andy.ecologyoauth2.util

import org.codehaus.jackson.map.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.StringUtils

class JsonPersistenceConverters<IN> {

    private val log: Logger = LoggerFactory.getLogger(JsonPersistenceConverters::class.java)

    private val objectMapper: ObjectMapper = ObjectMapper()

    /**
     * describe: 将输入数据转化为JSON
     * author 候帅
     * date 2018/8/18 下午3:51
     * @param input 输入的数据
     * @return   返回Json 数据
     */
    fun convertToJson(input: IN): String? {
        log.info("当前需要转化的json为：$input  " )


        if (input == null)
            return null
        try {
            return objectMapper.writeValueAsString(input)
        } catch (e: Exception) {
            log.error("Serialize $input error $e")
            throw RuntimeException(e)
        }
    }

    /**
     * describe: 进行将Json转化为对应的实体
     * author 候帅
     * date 2018/8/18 下午3:52
     * @param json 待转化的json字符
     * @param typeOfInput 输入类型
     * @return   对应输入类型的实体
     */
    fun convertFromJson(json: String, typeOfInput: Class<IN>): IN? {
        log.info("当前需要转化的json为：$json   --- 类型为：${typeOfInput.name}" )
        if (StringUtils.isEmpty(json))
            return null
        try {
            return objectMapper.readValue(json, typeOfInput)
        } catch (e: Exception) {
            log.error("Deserialize OAuth2AccessToken error $e")
            throw RuntimeException(e)
        }
    }
}