package com.daihuaiyu.springframework.beans.factory;

import com.daihuaiyu.springframework.beans.BeansException;

/**
 * BeanFactoryAware
 * @Author: hydai
 * @Date: 2021/7/3 07:47
 * @Description:实现此接口，能感知到所属的 BeanFactory
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
