package com.andy.service.servierusercenter.service

import com.andy.service.servierusercenter.entity.GroupIconEntity

/**
 * FileName: IGroupIconService
 * author:   候帅
 * data:     25/09/2018 22:59
 * Description: 组的图标管理
 * History:
 */
interface IGroupIconService {
    /**
     * describe: 增加组的ICON图表地址
     * author 候帅
     * date 2018/9/26 上午10:08
     * @param url 图表地址
     * @return 唯一ID
     */
    fun addGroupIcon(url: String): Long

    /**
     * describe: 删除组ICON 通过ID
     * author 候帅
     * data 26/09/2018 20:02
     * @param id 对应ID
     */
    fun deleteGroupIcon(id: Long)

    /**
     * describe: 发现所有内容
     * author 候帅
     * data 26/09/2018 20:08
     * @return 所有图标内容
     */
    fun findAll(): List<GroupIconEntity>

    /**
     * describe: 修改图标信息内容
     * author 候帅
     * data 26/09/2018 20:14
     */
    fun modiftyGroupIcon(id: Long, url: String)
}