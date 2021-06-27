package com.daihuaiyu.springframework.beans.factory.factory;

import com.daihuaiyu.springframework.beans.BeansException;

/**
 * 提供用户操作Bean类
 * @Author: hydai
 * @Date: 2021/6/27 09:18
 * @Description:用于用户实现该接口，在Bean执行初始化前后做自定义操作
 */
public interface BeanPostProcessor {

    /**
     * 在bean对象执行初始化之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessorBeforeInitialization(Object bean,String beanName) throws BeansException;

    /**
     * 在bean对象执行初始化之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessorAfterInitialization(Object bean,String beanName) throws BeansException;
}
