package com.andy.server.serveremail.entity

import com.andy.corejpa.AbstractIdAuditable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "email")
class EmailEntity: AbstractIdAuditable(){

    @NotNull
    @Size(max = 30)
    @Column(name = "text", nullable = false, length = 30)
    lateinit var text: String

    @NotNull
    @Size(max = 30)
    @Column(name = "receiver", nullable = false, length = 30)
    lateinit var receiver: String

    @NotNull
    @Size(max = 30)
    @Column(name = "sender", nullable = false, length = 30)
    lateinit var sender: String

    @Lob
    @Column(name = "subject", nullable = false)
    lateinit var subject: String

}