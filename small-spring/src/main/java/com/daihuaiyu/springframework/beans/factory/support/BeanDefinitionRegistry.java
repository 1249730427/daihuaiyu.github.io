package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;

/**
 * BeanDefinitionRegistry接口
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/25 10:05
 * @Description:
 */
public interface BeanDefinitionRegistry {

    /**
     * beanDefinition注册
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 判断容器中是否包含了这个name的Bean
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);
}
