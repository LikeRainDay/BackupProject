package com.andy.service.servierusercenter.service.impl

import com.andy.andycommonbean.exception.RepeatParamException
import com.andy.andycommonbean.request.PageParams
import com.andy.corejpa.utils.PageInfo
import com.andy.service.servicerusercenter.dao.PermissionDao
import com.andy.service.servierusercenter.entity.PermissionEntity
import com.andy.service.servierusercenter.service.IPermissionService
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
class IPermissionServiceImpl : IPermissionService {


    private val log: Logger = LoggerFactory.getLogger(IPermissionServiceImpl::class.java)


    @Autowired
    private lateinit var permissionDao: PermissionDao

    override fun addPermission(permission: PermissionEntity): String {
        return permissionDao.save(permission).id!!
    }

    override fun modiftyPermission(permission: PermissionEntity) {
        permissionDao.save(permission)
    }

    override fun deletePermission(perId: String) {
        permissionDao.deleteById(perId)
    }

    override fun findAll(): Optional<MutableList<PermissionEntity>> {
        return Optional.ofNullable(permissionDao.findAll().toMutableList())
    }

    override fun onCreatePermission(pmName: String, pmType: String): String? {
        val permissionEntity = permissionDao.findByPermissionName(pmName)
        if (permissionEntity.isPresent)
            throw RepeatParamException.error("No more pmName values are allowed in the database.")
        val entity = PermissionEntity()
        entity.permissionName = pmName
        entity.permissionType = pmType
        return permissionDao.save(entity).id
    }

    override fun onModiftyPermission(pmId: String, pmName: String?, pmType: String?) {
        val entity = permissionDao.findById(pmId)
        entity.ifPresent {
            if (pmName != null)
                it.permissionName = pmName
            if (pmType != null)
                it.permissionType = pmType
            permissionDao.save(it)
        }
    }

    override fun onPage(pageResult: PageParams): Page<PermissionEntity> {
        val sort = Sort(Sort.Direction.DESC, "createTime")
        val predicates = ArrayList<Predicate>()
        val pageable = PageInfo(pageResult.currentPage, pageResult.pageSize, sort)
        val spec = Specification<PermissionEntity> { root, _, criteriaBuilder ->
            if ("" != pageResult.searchValue) {
                predicates.add(criteriaBuilder.like(root.get("permission_name"), "%${pageResult.searchValue}%"))
            }
            if (pageResult.statis > -1) {
                predicates.add(criteriaBuilder.equal(root.get<Any>("status"), pageResult.statis))
            }
            return@Specification criteriaBuilder.and(*predicates.toTypedArray())
        }
        return permissionDao.findAll(spec, pageable)
    }

}