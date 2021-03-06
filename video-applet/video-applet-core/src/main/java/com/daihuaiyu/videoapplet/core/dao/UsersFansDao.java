package com.daihuaiyu.videoapplet.core.dao;

import com.daihuaiyu.videoapplet.core.domain.UsersFans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 用户关注取消关注接口
 *
 * @author daihuaiyu
 * @create: 2021-04-16 14:13
 **/
@Repository
public interface UsersFansDao extends JpaRepository<UsersFans,String>, JpaSpecificationExecutor<UsersFans> {
}
