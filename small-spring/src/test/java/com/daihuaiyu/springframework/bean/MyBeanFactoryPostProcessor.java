package com.daihuaiyu.springframework.bean;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.PropertyValue;
import com.daihuaiyu.springframework.beans.factory.PropertyValues;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;
import com.daihuaiyu.springframework.beans.factory.factory.BeanFactoryPostProcessor;
import com.daihuaiyu.springframework.beans.factory.factory.ConfigurableListableBeanFactory;

/**
 * MyBeanFactoryPostProcessor
 * @Author: daihuaiyu
 * @Date: 2021/6/28 17:04
 * @Description:
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param configurableListableBeanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BeanDefinition userService = configurableListableBeanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = userService.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company","字节跳动"));
    }
}
