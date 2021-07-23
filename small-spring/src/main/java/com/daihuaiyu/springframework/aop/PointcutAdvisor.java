package com.daihuaiyu.springframework.aop;

/**
 * Pointcut访问者
 * @Author: daihuaiyu
 * @Date: 2021/7/23 14:30
 * @Description:Advisor 承担了 Pointcut 和 Advice 的组合，Pointcut 用于获取 JoinPoint，而 Advice 决定于 JoinPoint 执行什么操作。
 */
public interface PointcutAdvisor extends Advisor{

    /**
     * Get the Pointcut that drives this advisor.
     */
    Pointcut getPointcut();
}
