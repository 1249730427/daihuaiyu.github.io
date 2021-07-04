package com.daihuaiyu.springframework.beans.factory.factory;

import com.daihuaiyu.springframework.beans.factory.ConfigurableBeanFactory;
import com.daihuaiyu.springframework.beans.factory.PropertyValues;

/**
 * Bean定义
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/24 16:31
 * @Description:
 */
public class BeanDefinition {

    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROPERTY;

    private Class bean;

    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;

    private String scope  = SCOPE_SINGLETON;

    private boolean singleton = true;

    private boolean prototype = false;

    public BeanDefinition(Class bean) {
        this.bean = bean;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class bean,  String initMethodName, String destroyMethodName) {
        this.bean = bean;
        this.propertyValues = new PropertyValues();
        this.initMethodName = initMethodName;
        this.destroyMethodName = destroyMethodName;
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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }

    public void setPrototype(boolean prototype) {
        this.prototype = prototype;
    }
}
