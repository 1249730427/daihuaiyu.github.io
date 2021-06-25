package com.daihuaiyu.springframework.beans.factory;

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
     Object getBean(String beanName);

     /**
      * 获取带有参数的Bean
      * @param beanName
      * @param args
      * @return
      */
     Object getBean(String beanName,Object ... args);

}
