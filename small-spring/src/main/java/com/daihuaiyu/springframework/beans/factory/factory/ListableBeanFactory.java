package com.daihuaiyu.springframework.beans.factory.factory;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.BeanFactory;

import java.util.Map;

/**
 * BeanFactory接口补充
 * @Author: hydai
 * @Date: 2021/6/28 07:36
 * @Description:
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     * @param requireType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String,T> getBeanOfType(Class<T> requireType) throws BeansException;

    /**
     * 返回注册表中的所有Bean名称
     * @return
     */
    String [] getBeanDefinitionNames();
}
