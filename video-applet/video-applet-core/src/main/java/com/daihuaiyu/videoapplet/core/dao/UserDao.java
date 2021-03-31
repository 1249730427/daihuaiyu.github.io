package com.daihuaiyu.videoapplet.core.dao;

import com.daihuaiyu.videoapplet.core.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户DAO接口
 *
 * @author daihuaiyu
 * @create: 2021-03-31 14:20
 **/
@Repository
public interface UserDao extends JpaRepository<Users,String> {
}
