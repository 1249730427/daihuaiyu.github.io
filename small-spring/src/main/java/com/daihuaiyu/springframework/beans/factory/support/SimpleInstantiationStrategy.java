package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * JDK实例化策略
 * @Author: daihuaiyu
 * @Date: 2021/6/25 13:19
 * @Description:
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {


    /***
     * 实例化Bean
     * @param beanDefinition :Bean定义
     * @param beanName :Bean名称
     * @param constructor :构造器
     * @param args ：构造器入数
     * @return
     * @throws BeansException
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBean();
        try {
            Object instance;
            if(null != constructor){
                //有参对象实例化
                instance = clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }else{
                //无参对象实例化
                instance = clazz.getDeclaredConstructor().newInstance();
            }
            return instance;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw  new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
