package com.daihuaiyu.springframework.context.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;
import com.daihuaiyu.springframework.beans.factory.factory.BeanFactoryPostProcessor;
import com.daihuaiyu.springframework.beans.factory.factory.BeanPostProcessor;
import com.daihuaiyu.springframework.beans.factory.factory.ConfigurableListableBeanFactory;
import com.daihuaiyu.springframework.context.ConfigurableApplicationContext;
import com.daihuaiyu.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

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

        //3.向容器中注册ApplicationContextAwareProcessor,让实现ApplicationContextAware的Bean对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //4.在Bean实例化之前，执行BeanFactoryProcessor(Invoke factory processors registered as beans in the context)
        invokeBeanFactoryProcessors(beanFactory);

        //5.BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        //6.提前实例化Bean单例对象
        beanFactory.preInstantiateSingletons();

    }

    /**
     * 虚拟机关闭钩子注册
     *
     * @throws BeansException
     */
    @Override
    public void registerShutdownHook() throws BeansException {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    /**
     * 关闭
     *
     * @throws BeansException
     */
    @Override
    public void close() throws BeansException {
        getBeanFactory().destroySingletons();
    }

    private void invokeBeanFactoryProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeanOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor:beanFactoryPostProcessorMap.values()){
            beanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeanOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor:beanPostProcessorMap.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * 获取Bean
     *
     * @param beanName
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    /**
     * 获取带有参数的Bean
     *
     * @param beanName
     * @param args
     * @return
     */
    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName,args);
    }

    /**
     * 根据请求的beanName和类类型返回对应的Bean对象
     *
     * @param beanName
     * @param requireType
     * @return
     * @throws BeansException
     */
    @Override
    public <T> T getBeanOfType(String beanName, Class<T> requireType) throws BeansException {
        return getBeanFactory().getBeanOfType(beanName,requireType);
    }

    /**
     * 按照类型返回 Bean 实例
     *
     * @param requireType
     * @return
     * @throws BeansException
     */
    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> requireType) throws BeansException {
            return getBeanFactory().getBeanOfType(requireType);
    }

    /**
     * 返回注册表中的所有Bean名称
     *
     * @return
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
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
