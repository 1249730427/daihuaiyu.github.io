package com.daihuaiyu.springframework.context.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.factory.ConfigurableListableBeanFactory;
import com.daihuaiyu.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * AbstractApplicationContext实现类
 * @Author: daihuaiyu
 * @Date: 2021/6/28 15:06
 * @Description:
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory defaultListableBeanFactory;
    /**
     * 获取BeanFactory
     *
     * @return
     * @throws BeansException
     */
    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() throws BeansException {
        return defaultListableBeanFactory;
    }

    /**
     * 初始化Bean工厂，并注册BeanDefinition
     *
     * @throws BeansException
     */
    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = createBeanFactory();
        loadDefinitions(defaultListableBeanFactory);
        this.defaultListableBeanFactory = defaultListableBeanFactory;

    }

    private DefaultListableBeanFactory createBeanFactory() {
        return  new DefaultListableBeanFactory();
    }

    protected abstract void loadDefinitions(DefaultListableBeanFactory defaultListableBeanFactory);

}
