package com.daihuaiyu.springframework.beans.factory;

import com.daihuaiyu.springframework.beans.factory.factory.BeanPostProcessor;
import com.daihuaiyu.springframework.beans.factory.factory.HierarchicalBeanFactory;
import com.daihuaiyu.springframework.beans.factory.factory.SingletonBeanRegistry;

/**
 * Bean工厂配置类
 * @Author: hydai
 * @Date: 2021/6/28 07:50
 * @Description:
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    //单例
    String SCOPE_SINGLETON ="singleton";

    //2.原型
    String SCOPE_PROPERTY ="property";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
