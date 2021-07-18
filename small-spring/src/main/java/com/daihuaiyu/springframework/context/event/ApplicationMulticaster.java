package com.daihuaiyu.springframework.context.event;

import com.daihuaiyu.springframework.context.ApplicationEvent;
import com.daihuaiyu.springframework.context.ApplicationListener;

/**
 * 事件广播器
 * @Author: hydai
 * @Date: 2021/7/15 07:03
 * @Description:
 */
public interface ApplicationMulticaster {

    /**
     * Add a listener to be notified of all events.
     * @param applicationListener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> applicationListener);

    /**
     * Remove a listener from the notification list.
     * @param applicationListener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> applicationListener);

    /**
     * Multicast the given application event to appropriate listeners.
     * @param applicationEvent the event to multicast
     */
    void multicasterEvent(ApplicationEvent applicationEvent);
}
