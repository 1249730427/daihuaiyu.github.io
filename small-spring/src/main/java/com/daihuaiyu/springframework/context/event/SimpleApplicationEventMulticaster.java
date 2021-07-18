package com.daihuaiyu.springframework.context.event;

import com.daihuaiyu.springframework.beans.factory.BeanFactory;
import com.daihuaiyu.springframework.context.ApplicationEvent;
import com.daihuaiyu.springframework.context.ApplicationListener;

/**
 * @ClassName: SimpleApplicationEventMulticaster
 * @Author: hydai
 * @Date: 2021/7/16 07:16
 * @Description:
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    /**
     * Multicast the given application event to appropriate listeners.
     *
     * @param applicationEvent the event to multicast
     */
    @Override
    public void multicasterEvent(ApplicationEvent applicationEvent) {
        for(ApplicationListener applicationListener:getApplicationListeners(applicationEvent)){
            applicationListener.onApplicationEvent(applicationEvent);
        }
    }
}
