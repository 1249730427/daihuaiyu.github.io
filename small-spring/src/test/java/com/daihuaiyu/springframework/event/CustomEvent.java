package com.daihuaiyu.springframework.event;

import com.daihuaiyu.springframework.context.event.ApplicationContextEvent;

/**
 * @ClassName: CustomEvent
 * @Author: hydai
 * @Date: 2021/7/17 08:06
 * @Description:
 */
public class CustomEvent extends ApplicationContextEvent {

    private Long id;

    private String message;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomEvent(Object source,Long id,String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
