package com.daihuaiyu.springframework.beans;

/**
 * 自定义异常
 * @Author: daihuaiyu
 * @Date: 2021/6/24 18:00
 * @Description:
 */
public class BeansException extends RuntimeException {

    public  BeansException(String message){
        super(message);
    }
    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
