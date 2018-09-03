package com.andy.service.servierusercenter.entity

import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "pm_menu")
class PmMenuEntity: AbstractEntity() {


    // 菜单URL
    @Size(max = 80)
    @Column(name = "menu_url", nullable = false, length = 80)
    lateinit var menuUrl: String

    // 菜单名称
    @Size(max = 30)
    @Column(name = "menu_name", nullable = false, length = 30)
    lateinit var menuName: String

    // 描述信息
    @Size(max = 200)
    @Column(name = "menu_des", length = 200)
    lateinit var menuDes: String

    // 菜单的父ID
    @Column(name = "menu_parent_id", nullable = true)
    lateinit var menuParentId: String


    // 权限映射
    @ManyToMany(mappedBy = "pmMenu", cascade = [CascadeType.DETACH, CascadeType.PERSIST])
    lateinit var permission: Set<PermissionEntity>
}