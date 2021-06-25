package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略接口
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/25 11:41
 * @Description:
 */
public interface InstantiationStrategy {

    /***
     * 实例化Bean
     * @param beanDefinition:Bean定义
     * @param beanName:Bean名称
     * @param constructor:构造器
     * @param args：构造器入数
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor,Object[] args) throws BeansException;
}
