package com.andy.service.servierusercenter.dao;


import com.andy.service.servierusercenter.entity.RoleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 作者：候帅
 * 时间：2018:09:01-19:26
 * 描述： TODO
 */

public interface RoleDao extends JpaRepository<RoleEntity, Long> {


}
