package com.andy.service.servicerusercenter.dao.dao;


import com.andy.service.servierusercenter.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * 作者：候帅
 * 时间：2018:09:01-19:26
 * 描述： 权限数据库访问层
 */

public interface PermissionDao extends JpaRepository<PermissionEntity, String>, JpaSpecificationExecutor<PermissionEntity> {

    /**
     * describe: 根据权限名 进行查询（ 权限名唯一 ）
     * author 候帅
     * date 2018/9/21 下午2:11
     */
    Optional<PermissionEntity> findByPermissionName(String permissionName);
}
