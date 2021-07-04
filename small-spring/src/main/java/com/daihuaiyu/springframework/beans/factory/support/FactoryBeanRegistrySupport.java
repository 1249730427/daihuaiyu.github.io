package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工厂Bean注册抽象类
 * @Author: hydai
 * @Date: 2021/7/3 09:34
 * @Description:
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    /**
     * Cache of singleton objects created by FactoryBeans: FactoryBean name --> object
     */
    private final Map<String,Object> factoryBeanObjectCache = new ConcurrentHashMap<String,Object>();

    protected Object getCachedObjectForFactoryBean(String beanName){
         Object obj = this.factoryBeanObjectCache.get(beanName);
         return obj !=NULL_OBJECT ?obj:null;
    }

    protected Object getObjectFromFactoryBean(FactoryBean factoryBean,String beanName){
        if(factoryBean.isSingleton()){
             Object object = factoryBeanObjectCache.get(beanName);
             if(object ==null){
                  object = this.doGetObjectFromFactoryBean(factoryBean, beanName);
                 this.factoryBeanObjectCache.put(beanName, (object != NULL_OBJECT ? object : null));
             }
             return (object != NULL_OBJECT ? object : null);
        }else{
            return doGetObjectFromFactoryBean(factoryBean, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factoryBean,final String beanName){
        try {
            return factoryBean.getObject();
        } catch (BeansException e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }
}
