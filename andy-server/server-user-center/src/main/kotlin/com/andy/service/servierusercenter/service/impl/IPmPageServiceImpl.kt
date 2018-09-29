package com.andy.service.servierusercenter.service.impl

import com.andy.andycommonbean.exception.RepeatParamException
import com.andy.andycommonbean.request.PageParams
import com.andy.corejpa.utils.PageInfo
import com.andy.service.servicerusercenter.dao.dao.PmPageDao
import com.andy.service.servierusercenter.entity.PmPageEntity
import com.andy.service.servierusercenter.service.IPmPageService
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
class IPmPageServiceImpl : IPmPageService {

    private val log: Logger = LoggerFactory.getLogger(IPmPageServiceImpl::class.java)

    @Autowired
    private lateinit var pageDao: PmPageDao

    override fun findPageByParam(pageRequest: PageParams): Page<PmPageEntity> {
        val sort = Sort(Sort.Direction.DESC, "createTime")
        val predicates = ArrayList<Predicate>()
        val pageable = PageInfo(pageRequest.currentPage, pageRequest.pageSize, sort)
        val spec = Specification<PmPageEntity> { root, _, criteriaBuilder ->
            if ("" != pageRequest.searchValue) {
                predicates.add(criteriaBuilder.like(root.get("operationName"), "%${pageRequest.searchValue}%"))
            }
            if (pageRequest.statis > -1) {
                predicates.add(criteriaBuilder.equal(root.get<Any>("status"), pageRequest.statis))
            }
            return@Specification criteriaBuilder.and(*predicates.toTypedArray())
        }
        return pageDao.findAll(spec, pageable)
    }

    override fun addPagePermission(pageCoding: String, pageName: String, pageDes: String): String? {
        val pageEntity = pageDao.findByPageCoding(pageCoding)
        if (pageEntity.isPresent)
            throw RepeatParamException.error()
        val pmPageEntity = PmPageEntity()
        pmPageEntity.pageCoding = pageCoding
        pmPageEntity.pageDes = pageDes
        pmPageEntity.pageName = pageName
        return pageDao.save(pmPageEntity).id
    }


    @Autowired
    private lateinit var pmPageDao: PmPageDao

    override fun addPermission(permission: PmPageEntity): String {
        return pmPageDao.save(permission).id!!
    }

    override fun modiftyPermission(permission: PmPageEntity) {
        pmPageDao.save(permission)
    }

    override fun deletePermission(perId: String) {
        pmPageDao.deleteById(perId)
    }

    override fun findAll(): Optional<MutableList<PmPageEntity>> {
        return Optional.ofNullable(pmPageDao.findAll().toMutableList())
    }


}