package com.daihuaiyu.springframework.aop;

/**
 * 切点表达式接口
 * @Author: hydai
 * @Date: 2021/7/18 06:30
 * @Description:
 */
public interface Pointcut {

    /**
     * Return the ClassFilter for this pointcut.
     * @return the ClassFilter (never <code>null</code>)
     */
    ClassFilter getClassFilter();

    /**
     * Return the MethodMatcher for this pointcut.
     * @return the MethodMatcher (never <code>null</code>)
     */
    MethodMatcher getMethodMatcher();
}
