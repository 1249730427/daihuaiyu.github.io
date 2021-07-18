package com.daihuaiyu.springframework.beans.factory.factory;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.ConfigurableBeanFactory;

/**
 * ConfigurableListableBeanFactory
 *
 * @Author: hydai
 * @Date: 2021/6/27 09:11
 * @Description:
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    /**
     * 根据BeanName获取BeanDefinition
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 预处理单例对象
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;

    /**
     * 添加BeanPostProcessor
     * @param beanPostProcessor
     * @throws BeansException
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws BeansException;
}
