package com.daihuaiyu.springframework.bean;

import java.util.StringJoiner;

/**
 * 测试类
 * @Author: daihuaiyu
 * @Date: 2021/6/24 16:44
 * @Description:
 */
public class UserService {

    private String userName;

    private UserDao userDao;

//    public UserService(String userName,UserDao userDao) {
//        this.userName = userName;
//        this.userDao = userDao;
//    }
//
//    public UserService() {
//    }

//    public void queryUserInfo(){
//        System.out.println("查询用户信息:"+userName);
//    }
    public void queryUserInfo(){
        System.out.println(new StringJoiner("").add("查询用户信息:").add((CharSequence) userDao.queryUserName(userName)));
}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" ").append(userName);
        return sb.toString();
    }
}
