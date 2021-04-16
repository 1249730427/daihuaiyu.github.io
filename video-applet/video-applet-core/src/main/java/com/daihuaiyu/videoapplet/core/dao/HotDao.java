package com.daihuaiyu.videoapplet.core.dao;

import com.daihuaiyu.videoapplet.core.domain.Hot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 热搜词接口
 *
 * @author daihuaiyu
 * @create: 2021-04-16 09:43
 **/
public interface HotDao extends JpaRepository<Hot,String>, JpaSpecificationExecutor<Hot> {

    /**通过Content查询 jpa自定义查询的固定格式findBy+对应条件的字段*/
    Hot findByContent(String content);

}
