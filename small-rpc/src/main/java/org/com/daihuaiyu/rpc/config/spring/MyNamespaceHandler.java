package org.com.daihuaiyu.rpc.config.spring;

import org.com.daihuaiyu.rpc.config.spring.bean.ConsumerBean;
import org.com.daihuaiyu.rpc.config.spring.bean.ProviderBean;
import org.com.daihuaiyu.rpc.config.spring.bean.ServerBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 解析自定义标签
 * @Author: daihuaiyu
 * @Date: 2021/7/26 16:49
 * @Description:
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport{

    @Override
    public void init() {
        registerBeanDefinitionParser("consumer",new MyBeanDefinitionParse(ConsumerBean.class));
        registerBeanDefinitionParser("provider",new MyBeanDefinitionParse(ProviderBean.class));
        registerBeanDefinitionParser("server",new MyBeanDefinitionParse(ServerBean.class));
    }
}
