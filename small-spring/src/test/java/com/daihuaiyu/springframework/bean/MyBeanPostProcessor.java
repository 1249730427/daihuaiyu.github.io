package com.daihuaiyu.springframework.bean;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.factory.BeanPostProcessor;

/**
 * MyBeanPostProcessor
 * @Author: daihuaiyu
 * @Date: 2021/6/28 17:12
 * @Description:
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    /**
     * 在bean对象执行初始化之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
//            userService.setLocation("改为：北京");
        }
        return bean;
    }

    /**
     * 在bean对象执行初始化之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
