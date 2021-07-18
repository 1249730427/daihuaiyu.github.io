package com.daihuaiyu.springframework.context.event;

/**
 * 上下文刷新事件
 * @Author: hydai
 * @Date: 2021/7/15 06:59
 * @Description:
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
