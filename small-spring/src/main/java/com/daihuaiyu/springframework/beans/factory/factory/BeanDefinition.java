package com.daihuaiyu.springframework.beans.factory.factory;

/**
 * Bean定义
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/24 16:31
 * @Description:
 */
public class BeanDefinition {

    private Class bean;

    public BeanDefinition(Class bean) {
        this.bean = bean;
    }

    public Class getBean() {
        return bean;
    }

    public void setBean(Class bean) {
        this.bean = bean;
    }
}
