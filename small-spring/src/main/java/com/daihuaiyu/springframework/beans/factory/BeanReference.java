package com.daihuaiyu.springframework.beans.factory;

/**
 * Bean引用
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/25 15:56
 * @Description:
 */
public class BeanReference {

    private final String beanName;


    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
