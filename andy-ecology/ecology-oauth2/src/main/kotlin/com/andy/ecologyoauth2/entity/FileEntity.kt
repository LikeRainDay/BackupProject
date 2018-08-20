package com.andy.ecologyoauth2.entity

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

/**
 * describe: 文件表
 * author 候帅
 * date 2018/8/20 下午3:53
 */
@Entity
@Table(name = "pm_file")
class FileEntity: AbstractEntity<Long>() {

}