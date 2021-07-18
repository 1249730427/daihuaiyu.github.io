package com.daihuaiyu.springframework.event;

import com.daihuaiyu.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @ClassName: CustomEventListener
 * @Author: hydai
 * @Date: 2021/7/17 08:11
 * @Description:
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
