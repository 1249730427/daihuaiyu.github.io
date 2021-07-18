package com.daihuaiyu.springframework.bean;

/**
 * @Interface: IUserService
 * @Author: hydai
 * @Date: 2021/7/18 10:26
 * @Description:
 */
public interface IUserService {

    String queryUserInfo();

    String register(String userName);
}
