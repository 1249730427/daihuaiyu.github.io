package com.study.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/***
 * 定义SpringBoot中全局异常捕获器
 *
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public String exceptionHandler(Exception e){
        System.out.println("未知异常！原因是:"+e);
        return e.getMessage();
    }

}
