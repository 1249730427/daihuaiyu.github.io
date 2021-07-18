package com.daihuaiyu.springframework.event;

import com.daihuaiyu.springframework.context.ApplicationListener;
import com.daihuaiyu.springframework.context.event.ContextClosedEvent;

/**
 * @ClassName: ContextClosedEventListener
 * @Author: hydai
 * @Date: 2021/7/17 09:04
 * @Description:
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
