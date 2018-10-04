package com.andy.database.databasearango.dao

import com.andy.database.databasearango.entity.Character
import com.arangodb.springframework.repository.ArangoRepository

/**
 * FileName: CharacterRepository
 * author:   候帅
 * data:     26/09/2018 22:18
 * Description: 实例实体
 * History:
 */
interface CharacterRepository: ArangoRepository<Character, String> {
}