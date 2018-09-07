package com.andy.service.servierusercenter.entity

import javax.persistence.*

@Entity
@Table(name = "pm_page")
class PmPageEntity: AbstractEntity() {


    // 页元素编码
    @Column(name = "page_coding", nullable = false, length = 50)
    lateinit var pageCoding: String


    // 权限映射
    @ManyToMany(mappedBy = "pmPage", cascade = [CascadeType.DETACH, CascadeType.PERSIST])
    lateinit var permission: Set<PermissionEntity>
}