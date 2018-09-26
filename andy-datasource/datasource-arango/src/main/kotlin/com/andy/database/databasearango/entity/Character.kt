package com.andy.database.databasearango.entity

import com.arangodb.springframework.annotation.Document
import com.arangodb.springframework.annotation.HashIndex
import org.springframework.data.annotation.Id

/**
 * FileName: Character
 * author:   候帅
 * data:     26/09/2018 22:16
 * Description: Arangodb的 Document的实体
 * History:
 */
@Document(value = "characters")
// 索引
@HashIndex(fields = ["name","id"], unique = true)
class Character {

    @Id
    lateinit var id: String

    lateinit var name: String

}