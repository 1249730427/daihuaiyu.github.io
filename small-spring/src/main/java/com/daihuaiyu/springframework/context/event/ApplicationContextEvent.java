package com.daihuaiyu.springframework.context.event;

import com.daihuaiyu.springframework.context.ApplicationContext;
import com.daihuaiyu.springframework.context.ApplicationEvent;

/**
 * Spring监听事件上下文
 * @Author: hydai
 * @Date: 2021/7/15 06:54
 * @Description:
 */
public class ApplicationContextEvent  extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
       return (ApplicationContext) getSource();
    }
}
