package com.andy.service.servierusercenter.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

/**
 * describe: 功能操作表
 * author 候帅
 * date 2018/8/22 上午11:01
 */
@Entity
@Table(name = "pm_features", indexes = [
    Index(name = "INDEX_OPERATION_CODE", columnList = "operation_coding", unique = true)
])
class PmFeaturesEntity : AbstractEntity() {


    // 当前操作的编码
    @Column(name = "operation_coding", nullable = false)
    lateinit var operationCoding: String

    // 当前操作的名字
    @Column(name = "operation_name", nullable = false)
    lateinit var operationName: String

    // 当前操作的父id
    @Column(name = "operation_parent_id", nullable = true)
    lateinit var parentId: String

//    // 当前操作的索引 eg: -1-2-
//    @Column(name = "operation_index", nullable = false)
//    lateinit var operationIndex: String

    // 权限映射
    @JsonIgnore
    @ManyToMany(mappedBy = "pmFeatures", cascade = [CascadeType.DETACH, CascadeType.PERSIST])
    lateinit var permission: Set<PermissionEntity>

}