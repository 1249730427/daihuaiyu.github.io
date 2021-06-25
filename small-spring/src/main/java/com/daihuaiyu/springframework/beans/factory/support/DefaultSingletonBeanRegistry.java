package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.beans.factory.factory.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例类接口实现
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/24 17:33
 * @Description:
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String,Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object singleBean = singletonObjects.get(beanName);
        return singleBean;
    }

    protected void addSingleton(String beanName,Object singletonObject){
        singletonObjects.put(beanName,singletonObject);
    }

}
