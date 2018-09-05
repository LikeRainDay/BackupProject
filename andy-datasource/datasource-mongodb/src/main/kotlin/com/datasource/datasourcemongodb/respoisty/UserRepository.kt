package com.datasource.datasourcemongodb.respoisty

import com.datasource.datasourcemongodb.entity.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: MongoRepository<User, String> {

    fun findByName(name: String): User
}