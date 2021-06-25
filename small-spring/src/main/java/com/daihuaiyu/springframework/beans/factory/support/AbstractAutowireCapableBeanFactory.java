package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;

/**
 * Bean工厂抽象类
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/24 18:09
 * @Description:
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition definition) throws BeansException {
        Object bean;
        try {
            //通过反射拿到具体的Bean实例
            bean = definition.getBean().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        //添加到单例容器中
        addSingleton(beanName,bean);
        return bean;
    }
}
