package service.impl;

import service.HelloService;

/**
 * 学习JDK动态代理方法实现类
 *
 * @author daihuaiyu
 * @create: 2021-01-21 14:47
 **/
public class SayServiceImpl implements HelloService {

    @Override
    public void hello(String str) {

        System.out.println("Say World:"+str);
    }
}

