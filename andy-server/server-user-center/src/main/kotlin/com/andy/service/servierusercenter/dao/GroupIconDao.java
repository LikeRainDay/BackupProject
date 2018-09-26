package com.andy.service.servierusercenter.dao;


import com.andy.service.servierusercenter.entity.GroupEntity;
import com.andy.service.servierusercenter.entity.GroupIconEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * 作者：候帅
 * 时间：2018:09:01-19:26
 * 描述： 组ID
 */

public interface GroupIconDao extends JpaRepository<GroupIconEntity, Long> {



}
