package com.daihuaiyu.springframework.beans.factory;


/**
 * BeanClassLoaderAware
 * @Author: hydai
 * @Date: 2021/7/3 07:50
 * @Description:实现此接口，能感知到所属的 ClassLoader
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
