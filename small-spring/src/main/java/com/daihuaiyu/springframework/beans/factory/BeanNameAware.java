package com.daihuaiyu.springframework.beans.factory;


/**
 * BeanNameAware
 * @Author: hydai
 * @Date: 2021/7/3 07:52
 * @Description:实现此接口，能感知到所属的 BeanName
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);
}
