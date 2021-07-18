package com.daihuaiyu.springframework;

import com.daihuaiyu.springframework.context.ApplicationEvent;

/**
 * 定义事件发布者
 * @Author: hydai
 * @Date: 2021/7/16 06:58
 * @Description:通知注册在这个应用程序中的所有监听器活动。 事件可以是框架事件(如requestthandledevent)或特定于应用程序的事件。
 */
public interface ApplicationEventPublisher {

    /**
     * Notify all listeners registered with this application of an application
     * event. Events may be framework events (such as RequestHandledEvent)
     * or application-specific events.
     * @param event the event to publish
     */
    void publishEvent(ApplicationEvent event);
}
