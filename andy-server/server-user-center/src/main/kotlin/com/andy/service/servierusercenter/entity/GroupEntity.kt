package com.andy.service.servierusercenter.entity

import com.andy.corejpa.AbstractOrganizeAuditable
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "groups")
class GroupEntity : AbstractOrganizeAuditable() {

    // 用户组名
    @NotNull
    @Column(name = "group_name", nullable = false)
    lateinit var groupName: String

    // 用户组ID  随机加密内容
    @NotNull
    @Column(name = "group_id", nullable = false)
    lateinit var groupId: String

    // 当前组的 父ID
    @NotNull
    @Column(name = "parent_id", nullable = false)
    lateinit var parentId: String

    // 用户组 描述
    @Column(name = "group_desc")
    lateinit var groupDesc: String

    // 组的Icon
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "icon_id")
    lateinit var icon: GroupIconEntity


    // 用户信息多对多
    @ManyToMany(mappedBy = "group", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    lateinit var user: Set<UserEntity>

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(name = "it_GR",
            joinColumns = [
                JoinColumn(name = "FK_GR_REF_USER", referencedColumnName = "id")
            ],
            inverseJoinColumns = [
                JoinColumn(name = "FK_GR_REF_ROLE", referencedColumnName = "id")
            ])
    lateinit var roles: MutableSet<RoleEntity>
}