package org.com.daihuaiyu.rpc.config.spring.bean;

import org.com.daihuaiyu.rpc.config.ProviderConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 生产者Bean
 * @Author: daihuaiyu
 * @Date: 2021/7/26 15:46
 * @Description:
 */
public class ProviderBean extends ProviderConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //发布生产者
        doExport();
    }
}
