package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.core.io.Resource;
import com.daihuaiyu.springframework.core.io.ResourceLoader;

/**
 * BeanDefinition阅读器
 * @Author: daihuaiyu
 * @Date: 2021/6/26 09:12
 * @Description:
 */
public interface BeanDefinitionReader {
    /**
     * 获取BeanDefinitionRegistry
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     * @return
     */
    ResourceLoader getResource();

    /**
     * 根据单个Resource加载BeanDefinition
     * @param resource
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 根据多个Resource加载BeanDefinition
     * @param resources
     */
    void loadBeanDefinitions(Resource ... resources) throws BeansException;

    /**
     * 根据文件位置加载BeanDefinition
     * @param location
     */
    void loadBeanDefinitions(String location) throws BeansException;
}
