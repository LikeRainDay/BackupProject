package com.andy.service.servierusercenter.dao;


import com.andy.service.servierusercenter.entity.RoleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 作者：候帅
 * 时间：2018:09:01-19:26
 * 描述： TODO
 */

public interface RoleDao extends JpaRepository<RoleEntity, String> {

    /**
     * describe: 进行查询是否有对应的数据
     * author 候帅
     * date 2018/9/1 下午7:41
     */
    Optional<RoleEntity> findDistinctFirstByStatusAndRoleNameAndRoleType(int statu, String roleName, String roleType);


}
