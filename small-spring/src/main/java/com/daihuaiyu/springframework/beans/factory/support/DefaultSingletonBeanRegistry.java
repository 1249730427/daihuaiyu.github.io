package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.DisposableBean;
import com.daihuaiyu.springframework.beans.factory.factory.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 单例类接口实现
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/24 17:33
 * @Description:
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * Internal marker for a null singleton object:
     * used as marker value for concurrent Maps (which don't support null values).
     */
    protected static final Object NULL_OBJECT = new Object();

    private Map<String,Object> singletonObjects = new HashMap<>();

    private final Map<String,DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object singleBean = singletonObjects.get(beanName);
        return singleBean;
    }

    protected void addSingleton(String beanName,Object singletonObject){
        singletonObjects.put(beanName,singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean disposableBean){
        disposableBeans.put(beanName,disposableBean);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject){
        singletonObjects.put(beanName,singletonObject);
    }

    public void destroySingletons(){
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] objects = keySet.toArray();
        for(int i= objects.length-1;i>=0;i--){
            String beanName = (String) objects[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
