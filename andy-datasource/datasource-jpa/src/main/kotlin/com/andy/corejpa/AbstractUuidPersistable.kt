package com.andy.corejpa

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.domain.Persistable
import org.springframework.data.jpa.domain.AbstractPersistable
import org.springframework.data.util.ProxyUtils
import org.springframework.lang.Nullable
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass


/**
 * describe: UUID 主键生成 抽象类
 * author 候帅
 * date 2018/8/22 下午3:59
 */
@MappedSuperclass
abstract class AbstractUuidPersistable : Persistable<String> {


    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Nullable
    private var id: String? = null

    override fun getId(): String? {
        return id
    }

    override fun isNew(): Boolean {
        return null == id
    }

    override fun equals(other: Any?): Boolean {

        if (null == other) {
            return false
        }

        if (this === other) {
            return true
        }

        if (javaClass != ProxyUtils.getUserClass(other)) {
            return false
        }

        val that = other as AbstractPersistable<*>

        return if (null == this.getId()) false else this.getId() == that.id
    }

    override fun hashCode(): Int {
        var hashCode = 17

        hashCode += if (null == getId()) 0 else getId()!!.hashCode() * 31

        return hashCode
    }

    override fun toString(): String {
        return String.format("Entity of type %s with id: %s", this.javaClass.name, getId())
    }
}