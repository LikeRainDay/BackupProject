package com.andy.database.coreelasticsearch.doc

import com.andy.database.coreelasticsearch.repository.TestRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class TestDoTest{

    private val log = LoggerFactory.getLogger(TestDoTest::class.java)

    @Autowired
    private lateinit var testRepository: TestRepository

    @Test
    fun TestRepository(): Unit {

        val testDo = TestDo()
        testDo.birthDate = Date()
        testDo.testNo = 123
        testDo.lastName = "帅"
        testDo.firstName ="候"

//        testRepository.save(testDo)

        val findAll = testRepository.findAll()
        findAll.forEach {
            log.info("查询到的内容为：$it")
        }

    }

}