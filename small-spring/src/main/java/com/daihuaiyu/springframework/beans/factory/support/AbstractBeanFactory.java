package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.BeanFactory;
import com.daihuaiyu.springframework.beans.factory.ConfigurableBeanFactory;
import com.daihuaiyu.springframework.beans.factory.FactoryBean;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;
import com.daihuaiyu.springframework.beans.factory.factory.BeanPostProcessor;
import com.daihuaiyu.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean工厂的抽象类
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/24 17:47
 * @Description:继承了 DefaultSingletonBeanRegistry，也就具备了使用单例注册类方法。实现接口 BeanFactory，
 * 主要是对单例 Bean 对象的获取以及在获取不到时需要拿到 Bean 的定义做相应 Bean 实例化操作
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
    /** BeanPostProcessors to apply in createBean */
    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();
    /**
     * 根据BeanName获取Bean信息
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName,null);
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
            return doGetBean(beanName,args);
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
        return (T) getBean(beanName);
    }

    /**
     * 根据Bean名称获取BeanDefinition
     * @param beanName
     * @return
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 根据Bean名称和definition创建Bean
     * @param beanName
     * @param definition
     * @param args
     * @return
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName,BeanDefinition definition,Object [] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessorList() {
        return beanPostProcessorList;
    }

    protected ClassLoader getBeanClassLoader(){
        return this.classLoader;
    }

    protected <T> T doGetBean(final String name,final Object [] args){
         Object sharedInstance = getSingleton(name);
         if(sharedInstance !=null){
             //如果是 FactoryBean，则需要调用 FactoryBean#getObject
             return (T) getObjectForBeanInstance(sharedInstance,name);
         }
         BeanDefinition beanDefinition = getBeanDefinition(name);
         Object bean = createBean(name, beanDefinition, args);
         return (T)getObjectForBeanInstance(bean,name);
    }

    private Object getObjectForBeanInstance(Object bean, String name) {
        if(! (bean instanceof FactoryBean)){
            return bean;
        }
         Object object = getCachedObjectForFactoryBean(name);
        if(object == null){
             FactoryBean<?> factoryBean = (FactoryBean<?>) bean;
             object = getObjectFromFactoryBean(factoryBean, name);
        }
        return object;
    }
}
