package com.daihuaiyu.springframework.beans.factory.support;

import com.daihuaiyu.springframework.context.support.AbstractRefreshableApplicationContext;

/**
 * xml抽象类
 * @Author: daihuaiyu
 * @Date: 2021/6/28 16:21
 * @Description:
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadDefinitions(DefaultListableBeanFactory defaultListableBeanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory,this);
        String [] configureLocation = getConfigurationLocations();
        if(configureLocation !=null){
            for(int i=0;i<configureLocation.length;i++){
                xmlBeanDefinitionReader.loadBeanDefinitions(configureLocation[i]);
            }
        }

    }

    protected abstract String[] getConfigurationLocations();
}
