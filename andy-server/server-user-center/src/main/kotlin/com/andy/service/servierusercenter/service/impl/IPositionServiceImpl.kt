package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.dao.PositionsDao
import com.andy.service.servierusercenter.entity.PositionsEntity
import com.andy.service.servierusercenter.service.IPositionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils
import java.util.*

@Service
class IPositionServiceImpl: IPositionService {


    @Autowired
    private lateinit var positionsDao: PositionsDao

    @Transactional
    override fun addPosition(position: PositionsEntity): Long {
        val superEntity = positionsDao.findBySuperUnitCode(position.superUnitCode)
        superEntity.ifPresent {
            if (it.isLeaf)
                it.isLeaf = false
            if (!it.isSub)
                it.isSub = true
            position.isLeaf = true
            position.positionIndex = "${it.positionIndex}-${positionsDao.count() + 1}-"
            positionsDao.save(it)
        }
        val save = positionsDao.save(position)
        return save.id!!
    }

    override fun deletePosition(positionId: Long) {
        positionsDao.deleteById(positionId)
    }

    override fun modiftyPostionInfo(positionInfo: PositionsEntity) {
        val positionCoding = positionInfo.positionCoding
        if (!StringUtils.isEmpty(positionCoding)){
            val positionEntity = positionsDao.findByPositionCoding(positionCoding)
            positionEntity.ifPresent {
                it.position = positionInfo.position
                positionsDao.save(it)
            }
        }
    }

    override fun findAll(): Optional<MutableList<PositionsEntity>> {
        return Optional.ofNullable(positionsDao.findAll().toMutableList())
    }

    override fun movePositionToTarget(targetId: String, position: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mergePosition(targetId: String, position: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}