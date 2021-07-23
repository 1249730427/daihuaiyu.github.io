package com.daihuaiyu.springframework.context.event;

import com.daihuaiyu.springframework.context.ApplicationContext;
import com.daihuaiyu.springframework.context.ApplicationEvent;

/**
 * 事件上下文
 * @Author: daihuaiyu
 * @Date: 2021/7/14 08:59
 * @Description:
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
