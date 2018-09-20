package com.andy.service.servierusercenter.service

import com.andy.service.servierusercenter.bean.GroupBean
import com.andy.service.servierusercenter.entity.GroupEntity
import java.util.*

/**
 * describe: 组相关管理
 * author 候帅
 * date 2018/9/1 上午12:19
 */
interface IGroupService {


    /**
     * describe: 根据组的ID 进行查询组的详细内容
     * author 候帅
     * date 2018/9/5 下午10:30
     * @param id  组的唯一ID
     * @return    组的详细信息
     */
    fun findGroupInfoById(id: String): Optional<GroupEntity>

    /**
     * describe: 根据组的编号 进行查询组的详细内容
     * author 候帅
     * date 2018/9/5 下午10:31
     * @param groupId 组的唯一编号
     * @return   组的详细信息
     */
    fun findGroupInfoByGroupId(groupId: String): Optional<GroupEntity>


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


    /**
     * describe: 通过父ID 进行查询所有的子信息
     * author 候帅
     * date 2018/9/19 下午5:16
     */
    fun findAllChildrenByParnetId(parentId: String): Optional<MutableList<GroupBean>>

    /**
     * describe: 通过父ID 进行查询下一级的子信息
     * author 候帅
     * date 2018/9/20 上午9:57
     * @param  parentId 父ID
     * @return  下一级子内容
     */
    fun findChildrenByParnetId(parentId: String): Optional<MutableList<GroupBean>>

}