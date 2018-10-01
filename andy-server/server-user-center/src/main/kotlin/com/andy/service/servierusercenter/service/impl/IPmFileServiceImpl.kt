package com.andy.service.servierusercenter.service.impl

import com.andy.andycommonbean.request.PageParams
import com.andy.corejpa.utils.PageInfo
import com.andy.service.servierusercenter.dao.PmFileDao
import com.andy.service.servierusercenter.entity.PmFileEntity
import com.andy.service.servierusercenter.service.IPmFileService
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
class IPmFileServiceImpl : IPmFileService {


    private val log: Logger = LoggerFactory.getLogger(IPmFileServiceImpl::class.java)

    @Autowired
    private lateinit var pmFileDao: PmFileDao

    override fun addPermission(permission: PmFileEntity): String {
        return pmFileDao.save(permission).id!!
    }

    override fun modiftyPermission(permission: PmFileEntity) {
        pmFileDao.save(permission)
    }

    override fun deletePermission(perId: String) {
        pmFileDao.deleteById(perId)
    }

    override fun findAll(): Optional<MutableList<PmFileEntity>> {
        return Optional.ofNullable(pmFileDao.findAll().toMutableList())
    }

    override fun addFilePermission(fileUrl: String): String {
        val fileEntiy = pmFileDao.findByFileUrl(fileUrl)
        fileEntiy.ifPresent {
            throw IllegalAccessException("")
        }
        val pmFileEntity = PmFileEntity()
        pmFileEntity.fileUrl = fileUrl
        return pmFileDao.save(pmFileEntity).id!!

    }

    override fun findPageByParam(pageRequest: PageParams): Page<PmFileEntity> {
        val sort = Sort(Sort.Direction.DESC, "createTime")
        val predicates = ArrayList<Predicate>()
        val pageable = PageInfo(pageRequest.currentPage, pageRequest.pageSize, sort)
        val spec = Specification<PmFileEntity> { root, _, criteriaBuilder ->
            if ("" != pageRequest.searchValue) {
                predicates.add(criteriaBuilder.like(root.get("file_url"), "%${pageRequest.searchValue}%"))
            }
            if (pageRequest.statis > -1) {
                predicates.add(criteriaBuilder.equal(root.get<Any>("status"), pageRequest.statis))
            }
            return@Specification criteriaBuilder.and(*predicates.toTypedArray())
        }
        return pmFileDao.findAll(spec, pageable)
    }

}