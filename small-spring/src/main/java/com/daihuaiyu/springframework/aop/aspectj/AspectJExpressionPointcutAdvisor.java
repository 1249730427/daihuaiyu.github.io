package com.daihuaiyu.springframework.aop.aspectj;

import com.daihuaiyu.springframework.aop.Pointcut;
import com.daihuaiyu.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * 切面包装类
 * @Author: daihuaiyu
 * @Date: 2021/7/23 14:35
 * @Description:AspectJExpressionPointcutAdvisor 实现了 PointcutAdvisor 接口，把切面 pointcut、拦截方法 advice 和具体的拦截表达式包装在一起。这样就可以在 xml 的配置中定义一个 pointcutAdvisor 切面拦截器了
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    //切面
    private AspectJExpressionPointcut aspectJExpressionPointcut;

    //具体拦截方法
    private Advice advice;

    //方法表达式
    private String expression;
    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a before advice, a throws advice, etc.
     *
     * @return the advice that should apply if the pointcut matches
     * @see MethodInterceptor
     * @see BeforeAdvice
     */
    @Override
    public Advice getAdvice() {
        return advice;
    }

    /**
     * Get the Pointcut that drives this advisor.
     */
    @Override
    public Pointcut getPointcut() {
        if(aspectJExpressionPointcut ==null){
            aspectJExpressionPointcut = new AspectJExpressionPointcut(expression);
        }
        return aspectJExpressionPointcut;
    }

    public void setAspectJExpressionPointcut(AspectJExpressionPointcut aspectJExpressionPointcut) {
        this.aspectJExpressionPointcut = aspectJExpressionPointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
