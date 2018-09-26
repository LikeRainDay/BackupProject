package com.andy.database.databasearango.config

import com.arangodb.ArangoDB
import com.arangodb.Protocol
import com.arangodb.springframework.annotation.EnableArangoRepositories
import com.arangodb.springframework.config.AbstractArangoConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

/**
 * FileName: ArangodbConfig
 * author:   候帅
 * data:     26/09/2018 20:50
 * Description: ArangoDb的配置内容
 * History:
 */
@Configuration
@EnableArangoRepositories
class ArangodbConfig : AbstractArangoConfiguration() {

    @Autowired
    private lateinit var arangodbProperties: ArangodbProperties

    override fun arango(): ArangoDB.Builder {
        return ArangoDB.Builder()
                .host(arangodbProperties.hosts, arangodbProperties.port)
                .user(arangodbProperties.user)
                .password(arangodbProperties.password)
    }

    override fun database(): String {
        return arangodbProperties.database
    }
}