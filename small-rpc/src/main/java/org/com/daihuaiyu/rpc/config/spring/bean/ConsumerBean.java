package org.com.daihuaiyu.rpc.config.spring.bean;

import org.com.daihuaiyu.rpc.config.ConsumerConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * 消费者Bean
 * @Author: daihuaiyu
 * @Date: 2021/7/26 16:53
 * @Description:
 */
public class ConsumerBean<T> extends ConsumerConfig<T> implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return refer();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
