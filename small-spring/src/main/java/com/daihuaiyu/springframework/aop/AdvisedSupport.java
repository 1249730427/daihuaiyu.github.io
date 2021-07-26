package com.daihuaiyu.springframework.aop;

import com.daihuaiyu.springframework.util.ClassUtils;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * 切面通知
 * @Author: hydai
 * @Date: 2021/7/18 07:09
 * @Description:包装切面通知信息
 */
public class AdvisedSupport {

    /**被代理的目标对象*/
    private TargetSource targetSource;

    /**方法拦截器*/
    private MethodInterceptor methodInterceptor;

    /**方法匹配器（检查目标方法是否符合通知条件）*/
    private MethodMatcher methodMatcher;

    /**是否是Cglib代理*/
    private boolean isProxyTargetClass =false;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public void setProxyTargetClass(Boolean proxyTargetClass) {
        isProxyTargetClass = proxyTargetClass;
    }

    public boolean isProxyTargetClass(){
        return isProxyTargetClass;
    }
}
