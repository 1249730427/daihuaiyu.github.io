package com.daihuaiyu.springframework.bean;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ProxyBeanFactory
 * @Author: hydai
 * @Date: 2021/7/4 07:55
 * @Description:
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws BeansException {
        InvocationHandler handler = (proxy, method, args) -> {

            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "小傅哥");
            hashMap.put("10002", "八杯水");
            hashMap.put("10003", "阿毛");

            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
