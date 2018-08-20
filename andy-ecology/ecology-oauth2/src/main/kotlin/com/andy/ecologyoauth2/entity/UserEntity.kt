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
    lateinit var roles: MutableSet<UserRoleXrefEntity>

    @OneToMany(mappedBy = "authority", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    lateinit var authorities: MutableSet<UserAuthorityXrefEntity>

    // 用户和用户组表
//    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
//    @JoinTable(name = "it_userGroup",
//            joinColumns = [
//                JoinColumn(name = "user_ref_user", referencedColumnName = "id")
//            ],
//            inverseJoinColumns = [
//                JoinColumn(name = "user_ref_group", referencedColumnName = "id")
//            ])
//    lateinit var userGroupId: MutableSet<GroupEntity>
}