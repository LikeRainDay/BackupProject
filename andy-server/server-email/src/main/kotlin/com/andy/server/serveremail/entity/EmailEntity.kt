package com.andy.server.serveremail.entity

import com.andy.corejpa.AbstractIdAuditable
import org.springframework.lang.Nullable
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

    // 发送的邮件类型  0: 普通邮件  1: 邮箱验证邮件
    var type: Int = 0

    // 邮件发送的验证码
    @Nullable
    lateinit var identiftyCode: String

}