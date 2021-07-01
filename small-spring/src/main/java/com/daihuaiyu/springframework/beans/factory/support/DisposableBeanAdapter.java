package com.daihuaiyu.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.DisposableBean;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;

import java.lang.reflect.Method;

/**
 * DisposableBean适配器类
 * @Author: hydai
 * @Date: 2021/7/1 21:35
 * @Description:
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final String beanName;

    private final Object bean;

    private String destroyMethodName;

    public DisposableBeanAdapter(String beanName, Object bean, BeanDefinition beanDefinition) {
        this.beanName = beanName;
        this.bean = bean;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    /**
     * 销毁
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method method = bean.getClass().getMethod(destroyMethodName);
            if (null == method) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            method.invoke(bean);
        }
    }
}
