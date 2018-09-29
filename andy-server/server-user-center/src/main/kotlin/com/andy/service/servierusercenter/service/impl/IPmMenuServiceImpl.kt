package com.andy.service.servierusercenter.service.impl

import com.andy.andycommonbean.exception.RepeatParamException
import com.andy.andycommonbean.request.PageParams
import com.andy.corejpa.utils.PageInfo
import com.andy.service.servicerusercenter.dao.PmMenuDao
import com.andy.service.servierusercenter.entity.PmMenuEntity
import com.andy.service.servierusercenter.service.IPmMenuService
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
class IPmMenuServiceImpl : IPmMenuService {

    private val log: Logger = LoggerFactory.getLogger(IPmMenuServiceImpl::class.java)

    @Autowired
    private lateinit var pmMenuDao: PmMenuDao

    override fun addPermission(permission: PmMenuEntity): String {
        return pmMenuDao.save(permission).id!!
    }

    override fun modiftyPermission(permission: PmMenuEntity) {
        pmMenuDao.save(permission)
    }

    override fun deletePermission(perId: String) {
        pmMenuDao.deleteById(perId)
    }

    override fun findAll(): Optional<MutableList<PmMenuEntity>> {
        return Optional.ofNullable(pmMenuDao.findAll().toMutableList())
    }

    override fun addMenPermission(menuUrl: String, menuName: String, menuDes: String, menuParentId: String?): String {
        val menuEntity = pmMenuDao.findByMenuUrl(menuUrl)
        if (menuEntity.isPresent)
            throw RepeatParamException.error("Repeat param value is $menuUrl in mysql")
        val pmMenuEntity = PmMenuEntity()
        pmMenuEntity.menuDes = menuDes
        pmMenuEntity.menuName = menuName
        if (menuParentId != null)
            pmMenuEntity.menuParentId = menuParentId
        pmMenuEntity.menuUrl = menuUrl
        val save = pmMenuDao.save(pmMenuEntity)
        return save.id!!
    }

    override fun findPageByParam(pageRequest: PageParams): Page<PmMenuEntity> {
        val sort = Sort(Sort.Direction.DESC, "createTime")
        val predicates = ArrayList<Predicate>()
        val pageable = PageInfo(pageRequest.currentPage, pageRequest.pageSize, sort)
        val spec = Specification<PmMenuEntity> { root, _, criteriaBuilder ->
            if ("" != pageRequest.searchValue) {
                predicates.add(criteriaBuilder.like(root.get("menu_name"), "%${pageRequest.searchValue}%"))
            }
            if (pageRequest.statis > -1) {
                predicates.add(criteriaBuilder.equal(root.get<Any>("status"), pageRequest.statis))
            }
            return@Specification criteriaBuilder.and(*predicates.toTypedArray())
        }
        return pmMenuDao.findAll(spec, pageable)

    }

}