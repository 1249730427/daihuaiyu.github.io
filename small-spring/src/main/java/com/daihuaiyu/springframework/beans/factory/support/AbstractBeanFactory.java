package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.BeanFactory;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;

/**
 * Bean工厂的抽象类
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/24 17:47
 * @Description:继承了 DefaultSingletonBeanRegistry，也就具备了使用单例注册类方法。实现接口 BeanFactory，
 * 主要是对单例 Bean 对象的获取以及在获取不到时需要拿到 Bean 的定义做相应 Bean 实例化操作
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 根据BeanName获取Bean信息
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        Object bean = getSingleton(beanName);
        if(bean !=null){
            return bean;
        }
        BeanDefinition definition = getBeanDefinition(beanName);
        return createBean(beanName,definition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName,BeanDefinition definition) throws BeansException;
}
