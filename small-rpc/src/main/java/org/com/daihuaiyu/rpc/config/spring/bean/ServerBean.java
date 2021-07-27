package org.com.daihuaiyu.rpc.config.spring.bean;

import org.com.daihuaiyu.rpc.config.ServerConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ServerBean
 * @Author: daihuaiyu
 * @Date: 2021/7/26 16:55
 * @Description:
 */
public class ServerBean extends ServerConfig implements InitializingBean, ApplicationContextAware {

    private transient ApplicationContext applicationContext;
    @Override
    public void afterPropertiesSet() throws Exception {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.format("服务端信息=> [注册中心地址：%s] [注册中心端口：%s] \r\n", host, port);
    }
}
