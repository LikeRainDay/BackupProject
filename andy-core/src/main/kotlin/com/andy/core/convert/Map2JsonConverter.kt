package com.andy.core.convert

import com.alibaba.fastjson.JSON
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.persistence.AttributeConverter

/**
 * describe: Map 转化为 JSON 形式
 * author 候帅
 * date 2018/8/27 下午5:34
 */
class Map2JsonConverter: AttributeConverter<Map<String, Any>, String> {

    private val log: Logger = LoggerFactory.getLogger(Map2JsonConverter::class.java)


    override fun convertToDatabaseColumn(attribute: Map<String, Any>?): String {
        return JSON.toJSONString(attribute)

    }

    override fun convertToEntityAttribute(dbData: String?): Map<String, Any>? {
        return JSON.parseObject(dbData, Map::class.java) as Map<String, Any>
    }


}