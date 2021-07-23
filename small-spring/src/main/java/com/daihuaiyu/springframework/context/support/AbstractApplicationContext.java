package com.daihuaiyu.springframework.context.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.factory.BeanFactoryPostProcessor;
import com.daihuaiyu.springframework.beans.factory.factory.BeanPostProcessor;
import com.daihuaiyu.springframework.beans.factory.factory.ConfigurableListableBeanFactory;
import com.daihuaiyu.springframework.context.ApplicationEvent;
import com.daihuaiyu.springframework.context.ApplicationListener;
import com.daihuaiyu.springframework.context.ConfigurableApplicationContext;
import com.daihuaiyu.springframework.context.event.ApplicationMulticaster;
import com.daihuaiyu.springframework.context.event.ContextClosedEvent;
import com.daihuaiyu.springframework.context.event.ContextRefreshedEvent;
import com.daihuaiyu.springframework.context.event.SimpleApplicationEventMulticaster;
import com.daihuaiyu.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * Spring应用上下文
 * @Author: hydai
 * @Date: 2021/6/28 06:46
 * @Description:
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    private static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME="applicationMulticaster";

    private ApplicationMulticaster applicationMulticaster;
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

        //6.初始化事件发布者
        initApplicationMulticaster();

        //7.注册事件监听器
        registerListeners();

        //8.提前实例化Bean单例对象
        beanFactory.preInstantiateSingletons();

        //9.发布容器刷新完成事件
        finishRefresh();
    }

    private void finishRefresh() {

        publishEvent(new ContextRefreshedEvent(this));
    }

    /**
     * Notify all listeners registered with this application of an application
     * event. Events may be framework events (such as RequestHandledEvent)
     * or application-specific events.
     *
     * @param event the event to publish
     */
    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationMulticaster.multicasterEvent(event);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeanOfType(ApplicationListener.class).values();
        for(ApplicationListener applicationListener:applicationListeners){
            applicationMulticaster.addApplicationListener(applicationListener);
        }
    }

    private void initApplicationMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,applicationMulticaster);
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
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));
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

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
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
