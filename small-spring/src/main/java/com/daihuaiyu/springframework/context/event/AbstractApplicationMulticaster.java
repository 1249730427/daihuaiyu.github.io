package com.daihuaiyu.springframework.context.event;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.BeanFactory;
import com.daihuaiyu.springframework.beans.factory.BeanFactoryAware;
import com.daihuaiyu.springframework.context.ApplicationEvent;
import com.daihuaiyu.springframework.context.ApplicationListener;
import com.daihuaiyu.springframework.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 事件广播器接口部分实现
 * @Author: hydai
 * @Date: 2021/7/15 07:30
 * @Description:
 */
public abstract class AbstractApplicationMulticaster implements ApplicationMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners =new LinkedHashSet<>();

    private BeanFactory beanFactory;
    /**
     * Add a listener to be notified of all events.
     *
     * @param applicationListener the listener to add
     */
    @Override
    public void addApplicationListener(ApplicationListener<?> applicationListener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) applicationListener);
    }

    /**
     * Remove a listener from the notification list.
     *
     * @param applicationListener the listener to remove
     */
    @Override
    public void removeApplicationListener(ApplicationListener<?> applicationListener) {
        applicationListeners.remove(applicationListener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory =beanFactory;
    }

    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event){
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for(ApplicationListener<ApplicationEvent> listener :applicationListeners){
            if(supportsEvent(listener,event)) allListeners.add(listener);
        }
        return allListeners;
    }

    /**
     * 监听器是否对该事件感兴趣
     * @param listener
     * @param event
     * @return
     */
    private boolean supportsEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) {
         Class<? extends ApplicationListener> listenerClass = listener.getClass();
        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 class
        //targetClass.getGenericInterfaces() 获取参数化对象类型，为ParameterizedType,ParameterizedType.getActualTypeArguments为获取对象的实际类型
         Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass)?listenerClass.getSuperclass():listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
        return eventClassName.isAssignableFrom(event.getClass());

    }
}
