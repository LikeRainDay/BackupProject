package com.andy.database.coreelasticsearch.doc

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import java.util.*

@Document(indexName = "company", type = "employe", shards = 8, replicas = 1)
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