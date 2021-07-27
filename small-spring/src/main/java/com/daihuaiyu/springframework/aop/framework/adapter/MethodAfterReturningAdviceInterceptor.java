package com.daihuaiyu.springframework.aop.framework.adapter;

import cn.hutool.core.lang.Assert;
import com.daihuaiyu.springframework.aop.AfterAdvice;
import com.daihuaiyu.springframework.aop.AfterReturningAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author: daihuaiyu
 * @Date: 2021/7/27 11:18
 * @Description:
 */
public class MethodAfterReturningAdviceInterceptor implements MethodInterceptor, AfterAdvice {

    private AfterReturningAdvice advice;

    public MethodAfterReturningAdviceInterceptor(AfterReturningAdvice advice) {
        Assert.notNull(advice, "Advice must not be null");
        this.advice = advice;
    }
    public MethodAfterReturningAdviceInterceptor() {
    }

    /**
     * Implement this method to perform extra treatments before and
     * after the invocation. Polite implementations would certainly
     * like to invoke {@link Joinpoint#proceed()}.
     *
     * @param invocation the method invocation joinpoint
     * @return the result of the call to {@link
     * Joinpoint#proceed()}, might be intercepted by the
     * interceptor.
     * @throws Throwable if the interceptors or the
     *                   target-object throws an exception.
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object retVal = invocation.proceed();
        this.advice.afterReturning(retVal,invocation.getMethod(),invocation.getArguments(),invocation.getThis());
        return retVal;
    }
}
