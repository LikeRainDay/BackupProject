package com.datasource.datasourcemongodb.entity

import org.springframework.data.annotation.Id


class User {

    @Id
    var id: Int = 0

    lateinit var name: String

    var age: Int = 0


    override fun toString(): String {
        return "User(id=$id, name='$name', age=$age)"
    }


}