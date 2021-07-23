package com.daihuaiyu.springframework.beans.factory.factory;

import com.daihuaiyu.springframework.beans.BeansException;

/**
 * InstantiationAwareBeanPostProcessor
 * @Author: daihuaiyu
 * @Date: 2021/7/23 15:54
 * @Description:
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * Apply this BeanPostProcessor <i>before the target bean gets instantiated</i>.
     * The returned bean object may be a proxy to use instead of the target bean,
     * effectively suppressing default instantiation of the target bean.
     *
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postBeanPostProcessorBeforeInstantiation(Class<?> clazz,String beanName) throws BeansException;
}
