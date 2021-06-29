package com.daihuaiyu.springframework.beans.factory;

/**
 *初始化方法接口
 * @Author: hydai
 * @Date: 2021/6/30 07:19
 * @Description:
 */
public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
