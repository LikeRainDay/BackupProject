package com.andy.service.servierusercenter.service

import com.andy.service.servierusercenter.entity.GroupEntity

/**
 * describe: 组相关管理
 * author 候帅
 * date 2018/9/1 上午12:19
 */
interface IGroupService {


    /**
     * describe: 增加组
     * author 候帅
     * date 2018/9/1 上午12:20
     * @param groupEntity 当前组的实体内容
     * @return 增加的组ID
     */
    fun addGroup(groupEntity: GroupEntity): String


    /**
     * describe: 删除组 （需要删除组下所有子组）
     * author 候帅
     * date 2018/9/1 上午12:20
     * @param groupId  组id
     */
    fun deleteGroupById(groupId: String)

    /**
     * describe: 修改组信息
     * author 候帅
     * date 2018/9/1 上午12:20
     * @param groupEntity 待修改的组信息内容
     */
    fun modiftyGroupInfo(groupEntity: GroupEntity)


    /**
     * describe: 修改组的上级单位（其下子单位也要跟着进行移动）并修改其单位所属人员组信息内容
     * author 候帅
     * date 2018/9/1 上午12:21
     * @param targetId 需要修改的上级ID
     * @param childId 待修改的子ID
     */
    fun modiftyGroupParent(targetId: String, childId: String)


    /**
     * describe: 组合并 （ 进行将当前组合并到其他分组上 并删除原有分组 ）并修改其单位所属人员组信息内容
     * author 候帅
     * date 2018/9/1 上午12:21
     * @param targetId 合并的对应
     * @param childId 待操作子对象
     */
    fun mergeGroup(targetId: String, childId: String)


}