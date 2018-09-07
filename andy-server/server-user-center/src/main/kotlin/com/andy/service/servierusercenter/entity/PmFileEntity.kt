package com.andy.service.servierusercenter.entity

import javax.persistence.*
import javax.validation.constraints.Size

/**
 * describe: 文件权限
 * author 候帅
 * date 2018/8/22 上午11:01
 */
@Entity
@Table(name = "pm_file")
class PmFileEntity: AbstractEntity() {


    // 当前类型 0 : 代表 文件  1: 代表文件夹
    var status = 0

    // 文件名
    @Size(max = 60)
    lateinit var fileName: String

    // 文件目录
    @Column(name = "file_dir", nullable = false)
    lateinit var fileDir: String

    // 权限映射
    @ManyToMany(mappedBy = "pmFile", cascade = [CascadeType.DETACH, CascadeType.PERSIST])
    lateinit var permission: Set<PermissionEntity>

}