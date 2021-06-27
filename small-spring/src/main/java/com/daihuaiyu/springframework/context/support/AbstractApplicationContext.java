package com.daihuaiyu.springframework.context.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.factory.ConfigurableListableBeanFactory;
import com.daihuaiyu.springframework.context.ConfigurableApplicationContext;
import com.daihuaiyu.springframework.core.io.DefaultResourceLoader;

/**
 * Spring应用上下文
 * @Author: hydai
 * @Date: 2021/6/28 06:46
 * @Description:
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {
        //1.创建BeanFactory,并加载BeanDefinition
        refreshBeanFactory();

        //2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3.在Bean实例化之前，执行BeanFactoryProcessor(Invoke factory processors registered as beans in the context)
        invokeBeanFactoryProcessors(beanFactory);

        //4.BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        //5.提前实例化Bean单例对象
        beanFactory.preInstantiateSingletons();

    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {

    }

    private void invokeBeanFactoryProcessors(ConfigurableListableBeanFactory beanFactory) {

    }

    /**
     * 获取BeanFactory
     * @return
     * @throws BeansException
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory() throws BeansException;

    /**
     * 初始化Bean工厂，并注册BeanDefinition
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

}
