package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.core.io.DefaultResourceLoader;
import com.daihuaiyu.springframework.core.io.ResourceLoader;

/**
 * Bean定义抽象类实现
 * @Author: hydai
 * @Date: 2021/6/26 09:23
 * @Description:
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry beanDefinitionRegistry;

    private final ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry){
        this(beanDefinitionRegistry,new DefaultResourceLoader());
    }
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader = resourceLoader;
    }

    /**
     * 获取BeanDefinitionRegistry
     *
     * @return
     */
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return beanDefinitionRegistry;
    }

    /**
     * 获取资源加载器
     *
     * @return
     */
    @Override
    public ResourceLoader getResource() {
        return resourceLoader;
    }
}
