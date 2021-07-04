package com.daihuaiyu.springframework.bean;

import cn.hutool.json.JSONUtil;
import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.*;
import com.daihuaiyu.springframework.context.ApplicationContext;
import com.daihuaiyu.springframework.context.ApplicationContextAware;

import java.util.StringJoiner;


/**
 * 测试类
 * @Author: daihuaiyu
 * @Date: 2021/6/24 16:44
 * @Description:
 */
public class UserService  {

//    private ApplicationContext applicationContext;
//
//    private BeanFactory beanFactory;
    private String userName;

    private String company;
    private String location;
//    private UserDao userDao;
    private IUserDao userDao;

//    public UserService(String userName,UserDao userDao) {
//        this.userName = userName;
//        this.userDao = userDao;
//    }
//

    public UserService() {
    }

    public String queryUserInfo() {
        return userDao.queryUserName(userName) + "," + company + "," + location;
    }
//    public UserService() {
//    }

    //    public void queryUserInfo(){
//        System.out.println("查询用户信息:"+userName);
//    }
//    public void queryUserInfo(){
////        System.out.println(new StringJoiner("").add("查询用户信息:").add((CharSequence) userDao.queryUserName(userName)));
//        System.out.println(this);
//}

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

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
//
//    public ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
//
//    public BeanFactory getBeanFactory() {
//        return beanFactory;
//    }

//    @Override
//    public String toString() {
//        return JSONUtil.toJsonStr(this);
//    }

//    /**
//     * 销毁
//     *
//     * @throws Exception
//     */
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("执行：UserService.destroy");
//    }
//
//    /**
//     * Bean 处理了属性填充后调用
//     *
//     * @throws Exception
//     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("执行：UserService.afterPropertiesSet");
//    }

//    @Override
//    public void setBeanClassLoader(ClassLoader classLoader) {
//        System.out.println("ClassLoader：" + classLoader);
//    }
//
//    @Override
//    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        this.beanFactory = beanFactory;
//    }
//
//    @Override
//    public void setBeanName(String name) {
//        System.out.println("Bean Name is：" + name);
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
}
