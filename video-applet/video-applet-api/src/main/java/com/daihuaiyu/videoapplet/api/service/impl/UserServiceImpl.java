package com.daihuaiyu.videoapplet.api.service.impl;

import com.daihuaiyu.videoapplet.api.service.UserService;
import com.daihuaiyu.videoapplet.core.dao.UserDao;
import com.daihuaiyu.videoapplet.core.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 用户相关接口实现
 *
 * @author daihuaiyu
 * @create: 2021-03-31 10:34
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据用户名称判断用户是否存在，用户名是唯一索引
     *
     * @param userName
     */
    @Override
    public boolean userExists(String userName) throws Exception {
        Users u = new Users();
        u.setNickname(userName);
        Example<Users> example = Example.of(u);
        Optional<Users> userDaoOne = userDao.findOne(example);
        return userDaoOne.isPresent();
    }

    /**
     * 保存用户信息
     *
     * @param users
     */
    @Transactional
    @Override
    public Users save(Users users) {
        return userDao.save(users);
    }

    /**
     * 根据用户名称查找用户信息，用户名是唯一索引
     *
     * @param userName
     */
    @Override
    public Users findByUserName(String userName) {
        Users u = new Users();
        u.setNickname(userName);
        final Example<Users> example = Example.of(u);
        Optional<Users> userDaoOne = userDao.findOne(example);
        Users users = null;
        if(userDaoOne.isPresent()) {
            users = userDaoOne.get();
        }
        return users;
    }
}

