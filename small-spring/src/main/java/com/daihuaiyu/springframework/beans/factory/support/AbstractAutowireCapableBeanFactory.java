package com.daihuaiyu.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.*;
import com.daihuaiyu.springframework.beans.factory.factory.AutowireCapableBeanFactory;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;
import com.daihuaiyu.springframework.beans.factory.factory.BeanPostProcessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Bean工厂抽象类
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/24 18:09
 * @Description:继承AbstractBeanFactory抽象方法，重写createBean方法，通过反射拿到Bean对象，并添加到单例容器中
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition definition,Object [] args) throws BeansException {
        Object bean;
        try {
            //创建Bean
            bean = createBeanInstance(definition,beanName,args);
            //给Bean注入属性
            applyPropertyValue(definition,beanName,bean);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, definition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        //注册实现DisposableBean接口的对象
        registerDisposableBeanIfNecessary(bean,beanName,definition);
        //判断是否是单例，是的话添加到单例容器中
        if(definition.isSingleton()){
            addSingleton(beanName,bean);
        }
        return bean;
    }

    protected void registerDisposableBeanIfNecessary(Object bean, String beanName, BeanDefinition definition) {
        if(!definition.isSingleton())return;  //非单例模式不执行销毁方法
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(definition.getDestroyMethodName())){
            registerDisposableBean(beanName,new DisposableBeanAdapter(beanName,bean,definition));
        }
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition definition) throws Exception {
        if(bean instanceof Aware){
            if(bean instanceof BeanFactoryAware){
                ((BeanFactoryAware)bean).setBeanFactory(this);
            }
            if(bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if(bean instanceof BeanNameAware){
                ((BeanNameAware)bean).setBeanName(beanName);
            }

        }
        Object wrappedBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);
        invokeInitMethods(beanName, wrappedBean, definition);
        wrappedBean = applyBeanPostProcessorAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition definition) throws Exception {
        //1. 实现接口 InitializingBean
        if(wrappedBean instanceof InitializingBean){
            ((InitializingBean) wrappedBean).afterPropertiesSet();  //调用接口的具体实现
        }
        //2.获取配置文件中的initMethod
        String initMethodName = definition.getInitMethodName();
            if(StrUtil.isNotEmpty(initMethodName) &&!(wrappedBean instanceof InitializingBean && "afterPropertiesSet".equals(initMethodName))){
                Method method = definition.getBean().getMethod(initMethodName);
                if(null == method){
                    throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
                }
                method.invoke(wrappedBean);//反射获取执行方法
        }
    }

    private void applyPropertyValue(BeanDefinition definition, String beanName, Object bean) {
        try {
            PropertyValues propertyValues = definition.getPropertyValues();
            //遍历所有参数
            for(PropertyValue propertyValue:propertyValues.getPropertyValues()){
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if(value instanceof BeanReference){
                    // A 依赖 B，获取 B 的实例化,循环依赖暂不做处理
                    BeanReference beanReference = (BeanReference) value;
                    value =getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean,name,value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    private Object createBeanInstance(BeanDefinition definition, String beanName, Object[] args) {
        Constructor constructorToUser = null;
        Class<?> bean = definition.getBean();
        Constructor<?>[] constructors = bean.getDeclaredConstructors();
        for(Constructor constructor:constructors){
            if(null != args && constructor.getParameters().length == args.length){ //这里仅仅做了数量的判断，Spring源码中对参数类型也做了对比
                constructorToUser = constructor;
                break;
            }
        }
        return instantiationStrategy.instantiate(definition,beanName,constructorToUser,args);
    }

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for(BeanPostProcessor beanPostProcessor:getBeanPostProcessorList()){
            Object current = beanPostProcessor.postProcessorBeforeInitialization(result, beanName);
            if(null == current){
                return result;
            }
            result =current;

        }
        return result;
    }

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessorList()) {
            Object current = processor.postProcessorAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }
}
