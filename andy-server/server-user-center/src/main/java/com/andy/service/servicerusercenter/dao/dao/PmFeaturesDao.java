package com.andy.service.servicerusercenter.dao.dao;


import com.andy.service.servierusercenter.entity.PmFeaturesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * 作者：候帅
 * 时间：2018:09:01-19:26
 * 描述： 操作权限的dao
 */

public interface PmFeaturesDao extends JpaRepository<PmFeaturesEntity, String>, JpaSpecificationExecutor<PmFeaturesEntity> {

    /**
     * describe: 根据操作编号进行获取对应的操作权限实体
     * author 候帅
     * date 2018/9/21 下午12:01
     * @param code 操作编码
     */
    Optional<PmFeaturesEntity> findByOperationCoding(String code);
}
