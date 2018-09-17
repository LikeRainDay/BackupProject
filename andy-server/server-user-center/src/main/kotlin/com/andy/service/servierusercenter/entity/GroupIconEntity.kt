package com.andy.service.servierusercenter.entity

import com.andy.corejpa.AbstractIdAuditable
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

/**
 * FileName: GroupIconEntity
 * author:   候帅
 * data:     17/09/2018 22:08
 * Description: 组Icon相关实体
 * History:
 */
@Entity
@Table(name = "groups")
class GroupIconEntity : AbstractIdAuditable() {

    @Column(name = "url")
    lateinit var url: String

    @JsonIgnore
    @OneToMany(cascade = [CascadeType.PERSIST], mappedBy = "icon", fetch = FetchType.LAZY)
    lateinit var group: Set<GroupEntity>

}