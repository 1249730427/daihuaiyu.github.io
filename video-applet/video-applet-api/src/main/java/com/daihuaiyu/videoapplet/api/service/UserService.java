package com.daihuaiyu.videoapplet.api.service;

import com.daihuaiyu.videoapplet.core.domain.Users;

/**
 * 用户相关接口interface
 *
 * @author daihuaiyu
 * @create: 2021-03-31 10:30
 **/
public interface UserService {

    /**根据用户名称判断用户是否存在，用户名是唯一索引*/
    boolean userExists(String userName) throws Exception;

    /**保存用户信息*/
    Users save(Users users);

    /**根据用户名称查找用户信息，用户名是唯一索引*/
    Users findByUserName(String userName);
}
