package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean工厂抽象类
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/25 10:08
 * @Description:实现BeanDefinitionRegistry，重写registerBeanDefinition方法，将BeanName和BeanDefinition放入到beanDefinitionMap、
 * ，继承AbstractAutowireCapableBeanFactory重写registerBeanDefinition，实现根据BeanName从beanDefinitionMap获取BeanDefinition
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * beanDefinition注册
     *
     * @param beanName
     * @param beanDefinition
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }

    /**
     * 判断容器中是否包含了这个name的Bean
     *
     * @param beanName
     * @return
     */
    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    /**
     * 根据Bean名称获取BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition definition = beanDefinitionMap.get(beanName);
        if(definition == null){
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return definition;
    }
}
