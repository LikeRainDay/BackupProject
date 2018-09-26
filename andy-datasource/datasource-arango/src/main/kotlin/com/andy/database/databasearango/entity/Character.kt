package com.andy.database.databasearango.entity

import com.arangodb.springframework.annotation.Document
import org.springframework.data.annotation.Id

/**
 * FileName: Character
 * author:   候帅
 * data:     26/09/2018 22:16
 * Description: Arangodb的 Document的实体
 * History:
 */
@Document(value = "characters")
class Character {

    @Id
    lateinit var id: String

    lateinit var name: String

}