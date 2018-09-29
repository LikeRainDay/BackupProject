package com.andy.service.servicerusercenter.dao.dao;


import com.andy.service.servierusercenter.entity.PmMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * 作者：候帅
 * 时间：2018:09:01-19:26
 * 描述： 进行操作权限菜单相关内容
 */

public interface PmMenuDao extends JpaRepository<PmMenuEntity, String>, JpaSpecificationExecutor<PmMenuEntity> {

    /**
     * describe: 通过唯一内容 MenuURl 进行查询
     * author 候帅
     * date 2018/9/21 上午9:43
     */
    Optional<PmMenuEntity> findByMenuUrl(String menuUrl);
}
