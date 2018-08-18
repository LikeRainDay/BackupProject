package com.andy.ecologyoauth2.entity

import org.hibernate.annotations.ColumnDefault
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
class UserEntity: AbstractAuditable<Long>() {

    companion object {
        const val NAME_REGEX: String = "^[A-Za-z0-9_]*$"
    }

    @Size(max = 50)
    @NotNull
    @Pattern(regexp = NAME_REGEX)
    @Column(name = "username", unique = true, nullable = false, length = 50)
    lateinit var username: String

    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    lateinit var password: String

    @NotNull
    @Column(nullable = false)
    @ColumnDefault(value = "False")
    var disabled: Boolean = false

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    lateinit var roles: Set<UserRoleXrefEntity>

    @OneToMany(mappedBy = "authority", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    lateinit var authorities: Set<UserAuthorityXrefEntity>

}