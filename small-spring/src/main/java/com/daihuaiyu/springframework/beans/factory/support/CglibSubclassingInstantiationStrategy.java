package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * Cglib实例化策略
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/25 13:31
 * @Description:
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    /***
     * 实例化Bean
     * @param beanDefinition :Bean定义
     * @param beanName :Bean名称
     * @param constructor :构造器
     * @param args ：构造器入数
     * @return
     * @throws BeansException
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBean());
        enhancer.setCallback(new NoOp() {

            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if(constructor ==null){
            return enhancer.create();
        }
        return enhancer.create(constructor.getParameterTypes(),args);
    }
}
