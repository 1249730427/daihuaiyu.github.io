package com.daihuaiyu.springframework.beans.factory;

import com.daihuaiyu.springframework.beans.BeansException;

/**
 * 工厂Bean
 * @Author: hydai
 * @Date: 2021/7/3 09:31
 * @Description:
 */
public interface FactoryBean<T> {

    <T> T getObject() throws BeansException;

    Class<?> getObjectType();

    boolean isSingleton();
}
