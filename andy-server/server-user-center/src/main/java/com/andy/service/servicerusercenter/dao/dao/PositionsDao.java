package com.andy.service.servicerusercenter.dao.dao;


import com.andy.service.servierusercenter.entity.PositionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 作者：候帅
 * 时间：2018:09:01-19:26
 * 描述： TODO
 * @author housh
 */

public interface PositionsDao extends JpaRepository<PositionsEntity, Long> {


    /**
     * describe: 根据职位编号查询职位信息
     * author 候帅
     * date 2018/9/1 下午8:20
     * @param positionCode 职位编码
     * @return 职位内容
     */
    Optional<PositionsEntity> findByPositionCoding(String positionCode);

    
    /**
     * describe: 查询上级的信息内容
     * author 候帅  
     * date 2018/9/1 下午11:46
     * @param superCode 上级单位信息
     * @return 当前查询到的返回内容
     */
    Optional<PositionsEntity> findBySuperUnitCode(String superCode);
    
}
