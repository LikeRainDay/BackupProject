package com.andy.service.servierusercenter.dao;


import com.andy.service.servierusercenter.entity.PmFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 作者：候帅
 * 时间：2018:09:01-19:26
 * 描述： 文件权限字典
 */

public interface PmFileDao extends JpaRepository<PmFileEntity, String> {

    
    Optional<PmFileEntity> findByFileUrl(String fileUrl);
}
