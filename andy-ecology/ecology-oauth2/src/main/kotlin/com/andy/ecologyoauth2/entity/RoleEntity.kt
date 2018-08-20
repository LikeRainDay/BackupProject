package com.andy.ecologyoauth2.entity

import org.hibernate.annotations.ColumnDefault
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "roles")
class RoleEntity: AbstractAuditable<Long>() {



    @NotNull
    @Column(name = "role_name", nullable = false, unique = true, length = 100)
    lateinit var name: String

    @NotNull
    @Column(nullable = false)
    @ColumnDefault("False")
    var disable: Boolean = false

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    lateinit var authorities: Set<RoleAuthorityXrefEntity>

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    lateinit var users: Set<UserRoleXrefEntity>

//    @ManyToMany(mappedBy = "roleId", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    lateinit var groupId:Set<GroupEntity>

}

