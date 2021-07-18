package com.daihuaiyu.springframework.aop;

/**
 * 类过滤接口
 * @Author: hydai
 * @Date: 2021/7/18 06:34
 * @Description:定义类匹配类，用于切点找到给定的接口和目标类。
 */
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class <?> clazz);
}
