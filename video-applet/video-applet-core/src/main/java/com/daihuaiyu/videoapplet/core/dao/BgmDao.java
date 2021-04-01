package com.daihuaiyu.videoapplet.core.dao;

import com.daihuaiyu.videoapplet.core.domain.Bgm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 背景音乐DAO接口
 *
 * @author daihuaiyu
 * @create: 2021-04-01 14:20
 **/
@Repository
public interface BgmDao extends JpaRepository<Bgm,String>, JpaSpecificationExecutor<Bgm> {
}
