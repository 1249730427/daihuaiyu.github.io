package com.daihuaiyu.springframework.beans.factory;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean工厂
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/24 16:34
 * @Description:
 */
public interface BeanFactory {

     /**获取Bean*/
     Object getBean(String beanName) throws BeansException;

     /**
      * 获取带有参数的Bean
      * @param beanName
      * @param args
      * @return
      */
     Object getBean(String beanName,Object ... args) throws BeansException;

     /**
      * 根据请求的beanName和类类型返回对应的Bean对象
      * @param beanName
      * @param requireType
      * @param <T>
      * @return
      * @throws BeansException
      */
     <T> T getBeanOfType(String beanName,Class<T> requireType) throws BeansException;

     /**
      * 根据请求的beanName和类类型返回对应的Bean对象
      * @param beanName
      * @param requireType
      * @param <T>
      * @return
      * @throws BeansException
      */
     <T> T getBean(String beanName,Class<T> requireType) throws BeansException;
}
