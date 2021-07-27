package com.daihuaiyu.springframework.aop;

import java.lang.reflect.Method;

/**
 * 后置返回通知
 * @Author: daihuaiyu
 * @Date: 2021/7/27 13:43
 * @Description:
 */
public interface AfterReturningAdvice extends AfterAdvice {

    void afterReturning(Object retVal, Method method,Object[] args,Object target) throws Throwable;
}
