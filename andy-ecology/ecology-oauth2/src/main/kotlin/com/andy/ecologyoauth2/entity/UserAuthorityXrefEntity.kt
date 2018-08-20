package com.andy.ecologyoauth2.entity

import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "user_authority_xref")
class UserAuthorityXrefEntity: AbstractAuditable<Long>() {



    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = [CascadeType.DETACH, CascadeType.REFRESH])
    @JoinColumn(name = "user_id", nullable = false)
    lateinit var user: UserEntity

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = [CascadeType.DETACH, CascadeType.REFRESH])
    @JoinColumn(name = "authority_id", nullable = false)
    @Where(clause = "disable = False")
    lateinit var authority: AuthorityEntity

}