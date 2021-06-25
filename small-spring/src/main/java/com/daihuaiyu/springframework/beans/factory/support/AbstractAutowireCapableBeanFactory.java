package com.daihuaiyu.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.BeanReference;
import com.daihuaiyu.springframework.beans.factory.PropertyValue;
import com.daihuaiyu.springframework.beans.factory.PropertyValues;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;
import java.lang.reflect.Constructor;

/**
 * Bean工厂抽象类
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/24 18:09
 * @Description:继承AbstractBeanFactory抽象方法，重写createBean方法，通过反射拿到Bean对象，并添加到单例容器中
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition definition,Object [] args) throws BeansException {
        Object bean;
        try {
            //创建Bean
            bean = createBeanInstance(definition,beanName,args);
            //给Bean注入属性
            applyPropertyValue(definition,beanName,bean);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        //添加到单例容器中
        addSingleton(beanName,bean);
        return bean;
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
            if(constructor.getParameters().length == args.length){ //这里仅仅做了数量的判断，Spring源码中对参数类型也做了对比
                constructorToUser = constructor;
                break;
            }
        }
        return instantiationStrategy.instantiate(definition,beanName,constructorToUser,args);
    }
}
