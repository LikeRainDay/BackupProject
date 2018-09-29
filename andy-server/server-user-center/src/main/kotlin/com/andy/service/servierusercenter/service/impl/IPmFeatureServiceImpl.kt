package com.andy.service.servierusercenter.service.impl

import com.andy.andycommonbean.exception.RepeatParamException
import com.andy.andycommonbean.request.PageParams
import com.andy.corejpa.utils.PageInfo
import com.andy.service.servicerusercenter.dao.PmFeaturesDao
import com.andy.service.servierusercenter.entity.PmFeaturesEntity
import com.andy.service.servierusercenter.service.IPmFeatureService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.criteria.Predicate

@Service
class IPmFeatureServiceImpl : IPmFeatureService {


    private val log: Logger = LoggerFactory.getLogger(IPmFeatureServiceImpl::class.java)

    @Autowired
    private lateinit var pmFeaturesDao: PmFeaturesDao

    override fun addPermission(permission: PmFeaturesEntity): String {
        return pmFeaturesDao.save(permission).id!!
    }

    override fun modiftyPermission(permission: PmFeaturesEntity) {
        pmFeaturesDao.save(permission)
    }

    override fun deletePermission(perId: String) {
        pmFeaturesDao.deleteById(perId)
    }

    override fun findAll(): Optional<MutableList<PmFeaturesEntity>> {
        return Optional.ofNullable(pmFeaturesDao.findAll().toMutableList())
    }

    override fun findPageByParam(pageRequest: PageParams): Page<PmFeaturesEntity> {
        val sort = Sort(Sort.Direction.DESC, "createTime")
        val predicates = ArrayList<Predicate>()
        val pageable = PageInfo(pageRequest.currentPage, pageRequest.pageSize, sort)
        val spec = Specification<PmFeaturesEntity> { root, _, criteriaBuilder ->
            if ("" != pageRequest.searchValue) {
                predicates.add(criteriaBuilder.like(root.get("operationName"), "%${pageRequest.searchValue}%"))
            }
            if (pageRequest.statis > -1) {
                predicates.add(criteriaBuilder.equal(root.get<Any>("status"), pageRequest.statis))
            }
            return@Specification criteriaBuilder.and(*predicates.toTypedArray())
        }
        return pmFeaturesDao.findAll(spec, pageable)
    }

    override fun addFeaturePermission(operationCode: String, parentId: String, operationName: String): String? {
        val pmFeaturesEntity = pmFeaturesDao.findByOperationCoding(operationCode)
        if (pmFeaturesEntity.isPresent)
            throw RepeatParamException.error("can't insert $operationCode, Because it's unique in db")
        val entity = PmFeaturesEntity()
        entity.operationCoding = operationCode
        entity.operationName = operationName
        entity.parentId = parentId
        return pmFeaturesDao.save(entity).id
    }
}