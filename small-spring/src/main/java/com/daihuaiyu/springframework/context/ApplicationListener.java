package com.daihuaiyu.springframework.context;

import java.util.EventListener;

/**
 * Listener接口
 * @Author: hydai
 * @Date: 2021/7/15 07:22
 * @Description:
 */
public interface ApplicationListener< E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}
