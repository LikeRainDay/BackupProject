package com.andy.service.servierusercenter.dao;


import com.andy.service.servierusercenter.entity.PmPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * 作者：候帅
 * 时间：2018:09:01-19:26
 * 描述： 页面元素实体内容
 */

public interface PmPageDao extends JpaRepository<PmPageEntity, String>, JpaSpecificationExecutor<PmPageEntity> {

    Optional<PmPageEntity> findByPageCoding(String pageCode);
}
