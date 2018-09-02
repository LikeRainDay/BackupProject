package com.andy.service.servierusercenter.dao;


import com.andy.service.servierusercenter.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * 作者：候帅
 * 时间：2018:09:01-19:26
 * 描述： TODO
 */

public interface GroupDao extends JpaRepository<GroupEntity, String> {


    /**
     * describe: 通过GroupId 进行查询对应的内容信息
     * author 候帅
     * date 2018/9/2 下午6:26
     * @param groupId 组ID信息
     * @return   返回对应的组信息
     */
    Optional<GroupEntity> findByGroupId(String groupId);


    /**
     * describe: 移除组下所有 下属组
     * author 候帅
     * date 2018/9/2 下午9:11
     * @param orgIndex 索引
     */
    void deleteByOrganizeIndexIsStartingWith(String orgIndex);


    /**
     * describe: 查询出所有该单位
     * author 候帅
     * date 2018/9/2 下午6:43
     * @param
     * @return
     */

    @Query("select ge from GroupEntity ge where ge.organizeIndex like '?1%'")
    Optional<List<GroupEntity>> findByOrganizeIndexsLike(String orgIndex);
}
