package com.datasource.datasourcemongodb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DatasourceMongodbApplication

fun main(args: Array<String>) {
    runApplication<DatasourceMongodbApplication>(*args)
}
