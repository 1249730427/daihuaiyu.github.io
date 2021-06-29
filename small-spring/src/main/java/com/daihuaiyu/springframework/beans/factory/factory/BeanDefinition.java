package com.daihuaiyu.springframework.beans.factory.factory;

import com.daihuaiyu.springframework.beans.factory.PropertyValues;

/**
 * Bean定义
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/24 16:31
 * @Description:
 */
public class BeanDefinition {

    private Class bean;

    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;

    public BeanDefinition(Class bean) {
        this.bean = bean;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class bean, PropertyValues propertyValues) {
        this.bean = bean;
        this.propertyValues = propertyValues==null?new PropertyValues():propertyValues;
    }

    public Class getBean() {
        return bean;
    }

    public void setBean(Class bean) {
        this.bean = bean;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }
}
