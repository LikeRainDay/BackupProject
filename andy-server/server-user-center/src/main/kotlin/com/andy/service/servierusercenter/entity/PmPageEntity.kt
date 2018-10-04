package com.andy.service.servierusercenter.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "pm_page")
class PmPageEntity : AbstractEntity() {


    // 页元素编码
    @Column(name = "page_coding", nullable = false, length = 50)
    lateinit var pageCoding: String

    // 元素名
    lateinit var pageName: String

    // 元素描述
    lateinit var pageDes: String

    // 权限映射
    @JsonIgnore
    @ManyToMany(mappedBy = "pmPage", cascade = [CascadeType.DETACH, CascadeType.PERSIST])
    lateinit var permission: Set<PermissionEntity>
}