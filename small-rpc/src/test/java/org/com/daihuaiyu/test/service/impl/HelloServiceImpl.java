package org.com.daihuaiyu.test.service.impl;

import org.com.daihuaiyu.test.service.HelloService;

/**
 * @Author: daihuaiyu
 * @Date: 2021/7/26 18:03
 * @Description:
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void echo() {
        System.out.println("hi itstack demo rpc");
    }
}
