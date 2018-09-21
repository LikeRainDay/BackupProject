package com.andy.service.servierusercenter.dao;


import com.andy.service.servierusercenter.entity.RoleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * 作者：候帅
 * 时间：2018:09:01-19:26
 * 描述： 角色ORM
 */

public interface RoleDao extends JpaRepository<RoleEntity, String>, JpaSpecificationExecutor<RoleEntity> {

    /**
     * describe: 进行查询是否有对应的数据
     * author 候帅
     * date 2018/9/1 下午7:41
     */
    Optional<RoleEntity> findDistinctFirstByStatusAndRoleName(int statu, String roleName);

    /**
     * describe: 获取角色对应的内容
     * author 候帅
     * date 2018/9/21 下午4:02
     */
    Optional<RoleEntity> findByRoleName(String roleName);
}
