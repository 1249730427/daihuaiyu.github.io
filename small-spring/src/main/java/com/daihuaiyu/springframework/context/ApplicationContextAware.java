package com.daihuaiyu.springframework.context;

import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.Aware;

/**
 * ApplicationContextAware
 * @Author: hydai
 * @Date: 2021/7/3 07:55
 * @Description:实现此接口，能感知到所属的 ApplicationContext
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
