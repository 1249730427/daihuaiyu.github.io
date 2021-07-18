package com.daihuaiyu.springframework.context;

import java.util.EventObject;

/**
 * 定义Spring监听事件
 * @Author: hydai
 * @Date: 2021/7/15 06:52
 * @Description:
 */
public abstract class ApplicationEvent extends EventObject {


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
