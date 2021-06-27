package com.daihuaiyu.springframework.beans.factory.factory;

import com.daihuaiyu.springframework.beans.BeansException;

/**
 * 提供用户操作Bean类
 * @Author: hydai
 * @Date: 2021/6/27 09:04
 * @Description:用于用户实现该接口，在Bean注册之后实例化之前做自定义操作
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     * @param configurableListableBeanFactory
     * @throws BeansException
     */
    void postProcessorBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException;
}
