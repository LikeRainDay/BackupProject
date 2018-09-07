package com.andy.database.coreelasticsearch.repository

import com.andy.database.coreelasticsearch.doc.TestDo
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface TestRepository: ElasticsearchRepository<TestDo, Int> {

}