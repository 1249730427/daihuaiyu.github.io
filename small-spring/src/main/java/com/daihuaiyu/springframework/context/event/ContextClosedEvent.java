package com.daihuaiyu.springframework.context.event;

/**
 * 上下文关闭事件
 * @Author: hydai
 * @Date: 2021/7/15 06:58
 * @Description:
 */
public class ContextClosedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
