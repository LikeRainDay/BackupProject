package com.andy.ecologyoauth2.entity

import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "role_authority_xref")
class RoleAuthorityXrefEntity: AbstractAuditable<Long>() {



    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = [CascadeType.DETACH, CascadeType.REFRESH])
    @JoinColumn(name = "role_id", nullable = false)
    lateinit var role: RoleEntity

    @JoinColumn(name = "authority_id", nullable = false)
    @Where(clause = "disable = False")
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = [CascadeType.DETACH, CascadeType.REFRESH])
    lateinit var authority: AuthorityEntity

}