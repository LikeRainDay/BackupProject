package com.andy.service.servierusercenter.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "permission", indexes = [
    (Index(name = "INDEX_NAME", columnList = "permission_name", unique = true))
])
class PermissionEntity : AbstractEntity() {


    // 权限名
    @Column(name = "permission_name", nullable = false)
    lateinit var permissionName: String

    // 权限类型
    @Column(name = "permission_type", nullable = false)
    lateinit var permissionType: String

    // 角色关联
    @JsonIgnore
    @ManyToMany(mappedBy = "permission", cascade = [CascadeType.PERSIST, CascadeType.DETACH])
    lateinit var role: Set<RoleEntity>


    // 权限关联其他分支权限内容
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(name = "it_PM_FILE",
            joinColumns = [
                JoinColumn(name = "FK_PMF_FILE_REF_PERMISSION", referencedColumnName = "id")
            ],
            inverseJoinColumns = [
                JoinColumn(name = "FK_PMF_FILE_REF_FILE", referencedColumnName = "id")
            ])
    lateinit var pmFile: Set<PmFileEntity>


    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(name = "it_PM_FEATURE",
            joinColumns = [
                JoinColumn(name = "FK_PM_FEATURE_REF_PERMISSION", referencedColumnName = "id")
            ],
            inverseJoinColumns = [
                JoinColumn(name = "FK_PM_FEATURE_REF_FEATURE", referencedColumnName = "id")
            ])
    lateinit var pmFeatures: Set<PmFeaturesEntity>

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(name = "it_PM_MENU",
            joinColumns = [
                JoinColumn(name = "FK_PM_MENU_REF_PERMISSION", referencedColumnName = "id")
            ],
            inverseJoinColumns = [
                JoinColumn(name = "FK_PM_MENU_REF_MENU", referencedColumnName = "id")
            ])
    lateinit var pmMenu: Set<PmMenuEntity>

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(name = "it_PM_PAGE",
            joinColumns = [
                JoinColumn(name = "FK_PM_PAGE_REF_PERMISSION", referencedColumnName = "id")
            ],
            inverseJoinColumns = [
                JoinColumn(name = "FK_PM_PAGE_REF_PAGE", referencedColumnName = "id")
            ])
    lateinit var pmPage: Set<PmPageEntity>

}