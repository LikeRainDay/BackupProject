package com.andy.service.servierusercenter.service

import com.andy.andycommonbean.request.PageRequest
import com.andy.service.servierusercenter.entity.PmFeaturesEntity
import org.springframework.data.domain.Page


interface IPmFeatureService : BasePermissionService<PmFeaturesEntity> {

    /**
     * describe: 根据分页参数获取对应 分页后的数据
     * author 候帅
     * date 2018/9/21 上午11:42
     * @param pageRequest 分页参数
     * @return   分页后的结果
     */
    fun findPageByParam(pageRequest: PageRequest): Page<PmFeaturesEntity>

    /**
     * describe: 增加特征权限内容
     * author 候帅  
     * date 2018/9/21 上午11:47  
     * @param 
     * @return   
     */  
    fun addFeaturePermission(operationCode: String, parentId: String, operationName: String): String?
}