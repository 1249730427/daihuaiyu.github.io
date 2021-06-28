package com.daihuaiyu.springframework.bean;

import cn.hutool.json.JSONUtil;

import java.util.StringJoiner;


/**
 * 测试类
 * @Author: daihuaiyu
 * @Date: 2021/6/24 16:44
 * @Description:
 */
public class UserService {

    private String userName;

    private String company;
    private String location;
    private UserDao userDao;

//    public UserService(String userName,UserDao userDao) {
//        this.userName = userName;
//        this.userDao = userDao;
//    }
//


    public UserService() {
    }

    //    public void queryUserInfo(){
//        System.out.println("查询用户信息:"+userName);
//    }
    public void queryUserInfo(){
//        System.out.println(new StringJoiner("").add("查询用户信息:").add((CharSequence) userDao.queryUserName(userName)));
        System.out.println(this);
}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
