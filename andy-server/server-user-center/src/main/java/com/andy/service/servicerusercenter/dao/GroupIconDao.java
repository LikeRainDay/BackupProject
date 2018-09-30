package com.andy.service.servicerusercenter.dao;


import com.andy.service.servierusercenter.entity.GroupIconEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 作者：候帅
 * 时间：2018:09:01-19:26
 * 描述： 组ID
 */
@Repository
public interface GroupIconDao extends JpaRepository<GroupIconEntity, Long> {



}
