package com.daihuaiyu.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * Advisor访问者
 * @Author: daihuaiyu
 * @Date: 2021/7/23 14:28
 * @Description:
 */
public interface Advisor {

    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a before advice, a throws advice, etc.
     * @return the advice that should apply if the pointcut matches
     * @see org.aopalliance.intercept.MethodInterceptor
     * @see BeforeAdvice
     */
    Advice getAdvice();
}
