package com.daihuaiyu.springframework.event;

import com.daihuaiyu.springframework.context.ApplicationListener;
import com.daihuaiyu.springframework.context.event.ContextRefreshedEvent;

/**
 * @ClassName: ContextRefreshedEventListener
 * @Author: hydai
 * @Date: 2021/7/17 09:00
 * @Description:
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
