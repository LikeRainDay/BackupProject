package com.andy.service.servierusercenter.service

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
}