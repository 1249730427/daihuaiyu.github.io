package com.daihuaiyu.springframework.beans.factory.factory;

/**
 * 单例类接口定义
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/24 17:30
 * @Description:
 */
public interface SingletonBeanRegistry {

    /**获取单例对象*/
    Object getSingleton(String beanName);

    /***
     * 注册单例对象
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);
}
