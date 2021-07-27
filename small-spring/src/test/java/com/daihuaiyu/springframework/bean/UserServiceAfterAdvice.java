package com.daihuaiyu.springframework.bean;

import com.daihuaiyu.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @Author: daihuaiyu
 * @Date: 2021/7/27 11:27
 * @Description:
 */
public class UserServiceAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object retVal, Method method, Object[] args, Object target) throws Throwable {
        if(method.getName().equals("queryUserInfo")){
            retVal = ((String)retVal).substring(3);
        }
        System.out.println("拦截方法："+method.getName()+" retVal="+retVal);
    }
}
