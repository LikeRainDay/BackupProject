package com.andy.server.serveremail.entity

import com.andy.corejpa.AbstractIdAuditable
import org.springframework.lang.Nullable
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "email",
        indexes = [
            Index(name = "INDEX_receiver", columnList = "receiver", unique = false)
        ])
class EmailEntity: AbstractIdAuditable(){

    @NotNull
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "text", nullable = false, columnDefinition = "TEXT")
    lateinit var text: String

    @NotNull
    @Column(name = "receiver", nullable = false)
    lateinit var receiver: String

    @NotNull
    @Size(max = 100)
    @Column(name = "sender", nullable = false, length = 30)
    lateinit var sender: String

    @Size(max = 30)
    @Column(name = "subject", nullable = false, length = 30)
    lateinit var subject: String

    // 发送的邮件类型  0: 普通邮件  1: 邮箱验证邮件
    var type: Int = 0

    // 邮件发送的验证码
    @Nullable
    lateinit var identiftyCode: String

}