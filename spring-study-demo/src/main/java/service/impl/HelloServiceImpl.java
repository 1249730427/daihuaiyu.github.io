package service.impl;

import service.HelloService;

/**
 * 学习JDK动态代理方法实现类
 *
 * @author daihuaiyu
 * @create: 2021-01-21 14:24
 **/
public class HelloServiceImpl implements HelloService {

    @Override
    public void hello(String str) {

        System.out.println("Hello World："+str);
    }
}

