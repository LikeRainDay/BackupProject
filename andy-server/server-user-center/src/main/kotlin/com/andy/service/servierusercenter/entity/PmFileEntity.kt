package com.andy.service.servierusercenter.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.Size

/**
 * describe: 文件权限
 * author 候帅
 * date 2018/8/22 上午11:01
 */
@Entity
@Table(name = "pm_file", indexes = [
    Index(name = "INDEX_FILE_URL", columnList = "file_url", unique = true)
])
class PmFileEntity : AbstractEntity() {

    // 文件名
    @Column(name = "file_url", nullable = false)
    lateinit var fileUrl: String

    // 权限映射
    @JsonIgnore
    @ManyToMany(mappedBy = "pmFile", cascade = [CascadeType.DETACH, CascadeType.PERSIST])
    lateinit var permission: Set<PermissionEntity>

}