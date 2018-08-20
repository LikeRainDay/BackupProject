package com.andy.ecologyoauth2.entity

import org.hibernate.annotations.Where
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "user_role_xref")
class UserRoleXrefEntity: AbstractAuditable<Long>() {


    @NotNull
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(cascade = [CascadeType.REFRESH, CascadeType.DETACH], fetch = FetchType.EAGER)
    lateinit var user: UserEntity

    @NotNull
    @ManyToOne(cascade = [CascadeType.REFRESH, CascadeType.DETACH], fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    @Where(clause = "disabled = False")
    lateinit var role: RoleEntity

}