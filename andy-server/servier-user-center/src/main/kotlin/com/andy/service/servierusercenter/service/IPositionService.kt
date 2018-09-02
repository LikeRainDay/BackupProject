package com.andy.service.servierusercenter.service

import com.andy.service.servierusercenter.entity.PositionsEntity
import java.util.*


/**
 * describe: 职位相关的
 * author 候帅
 * date 2018/9/1 下午6:45
 */
interface IPositionService {

    /**
     * describe: 新增职位
     * author 候帅
     * date 2018/9/1 下午6:45
     * @param position 新增的职位相关信息
     * @return 返回对应职位的 唯一ID
     */
    fun addPosition(position: PositionsEntity): Long

    /**
     * describe: 删除职位（ 需要进行伪删除）
     * author 候帅
     * date 2018/9/1 下午6:45
     * @param positionId 职位的唯一信息
     */
    fun deletePosition(positionId: Long)

    /**
     * describe: 修改职位信息 （ 需要进行保留 修改历史记录）
     * author 候帅
     * date 2018/9/1 下午6:45
     * @param
     * @return
     */
    fun modiftyPostionInfo(positionInfo: PositionsEntity)

    /**
     * describe: 查询所有职位
     * author 候帅
     * date 2018/9/1 下午6:46
     * @param
     * @return
     */
    fun findAll(): Optional<MutableList<PositionsEntity>>

    /**
     * describe: 移动职位 （ 需要将其下属所有单位进行迁移到目标职位下 ）
     * author 候帅
     * date 2018/9/1 下午6:46
     * @param targetId 需要移动到的职位下
     * @param postion 待移动职位
     */
    fun movePositionToTarget(targetId: String, position: String)

    /**
     * describe: 合并职位 （ 需要将当前职位进行相关合并，并修改对应职位下的人员信息 ）
     * author 候帅
     * date 2018/9/1 下午6:48
     * @param targetId 待合并到此单位
     * @param position 待合并的旧单位
     */
    fun mergePosition(targetId: String, position: String)


}