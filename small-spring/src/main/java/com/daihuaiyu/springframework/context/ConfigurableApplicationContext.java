package com.daihuaiyu.springframework.context;

import com.daihuaiyu.springframework.beans.BeansException;

/**
 *ConfigurableApplicationContext
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/27 09:39
 * @Description:
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
