package com.andy.service.servierusercenter.service.impl

import com.andy.service.servierusercenter.entity.PositionsEntity
import com.andy.service.servierusercenter.service.IPositionService
import org.springframework.stereotype.Service
import java.util.*

@Service
class IPositionServiceImpl: IPositionService {



    override fun addPosition(position: PositionsEntity): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletePosition(positionId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modiftyPostionInfo(positionInfo: PositionsEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): Optional<List<PositionsEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun movePositionToTarget(targetId: String, position: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mergePosition(targetId: String, position: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}