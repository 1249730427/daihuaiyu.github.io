package com.daihuaiyu.springframework.aop.framework.autoproxy;

import com.daihuaiyu.springframework.aop.*;
import com.daihuaiyu.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.daihuaiyu.springframework.aop.framework.ProxyFactory;
import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.BeanFactory;
import com.daihuaiyu.springframework.beans.factory.BeanFactoryAware;
import com.daihuaiyu.springframework.beans.factory.factory.InstantiationAwareBeanPostProcessor;
import com.daihuaiyu.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * 自动代理创建者
 * @Author: daihuaiyu
 * @Date: 2021/7/23 15:58
 * @Description:
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    /**
     * 在bean对象执行初始化之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 在bean对象执行初始化之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * Apply this BeanPostProcessor <i>before the target bean gets instantiated</i>.
     * The returned bean object may be a proxy to use instead of the target bean,
     * effectively suppressing default instantiation of the target bean.
     * <p>
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param clazz
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postBeanPostProcessorBeforeInstantiation(Class<?> clazz, String beanName) throws BeansException {

        if(isInfrastructureClass(clazz)) return null;
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeanOfType(AspectJExpressionPointcutAdvisor.class).values();
        for(AspectJExpressionPointcutAdvisor advisor:advisors){
            Pointcut pointcut = advisor.getPointcut();
            if(!pointcut.getClassFilter().matches(clazz)) continue;
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource target;
            try {
                target = new TargetSource(clazz.getDeclaredConstructor().newInstance());
            }catch (Exception e){
                throw new BeansException(e.getCause().getMessage());
            }
            advisedSupport.setTargetSource(target);
            advisedSupport.setMethodMatcher(pointcut.getMethodMatcher());
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    private boolean isInfrastructureClass(Class<?> clazz) {

        return Advice.class.isAssignableFrom(clazz) || Pointcut.class.isAssignableFrom(clazz) || Advisor.class.isAssignableFrom(clazz);
    }
}
