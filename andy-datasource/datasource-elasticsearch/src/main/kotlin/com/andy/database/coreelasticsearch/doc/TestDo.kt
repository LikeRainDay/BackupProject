package com.andy.database.coreelasticsearch.doc

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Mapping
import org.springframework.data.elasticsearch.annotations.Setting
import java.util.*

@Document(indexName = "company", type = "test", shards = 8, replicas = 1)
//@Mapping(mappingPath = "")
//@Setting(settingPath = "")
class TestDo {

    @Id
    var testNo: Int = 0

    lateinit var birthDate: Date

    lateinit var firstName: String

    lateinit var lastName: String


    override fun toString(): String {
        return "TestDo(testNo=$testNo, birthDate=$birthDate, firstName='$firstName', lastName='$lastName')"
    }
}