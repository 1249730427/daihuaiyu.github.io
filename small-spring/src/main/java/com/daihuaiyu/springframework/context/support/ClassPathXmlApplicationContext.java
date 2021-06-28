package com.daihuaiyu.springframework.context.support;

import com.daihuaiyu.springframework.beans.factory.support.AbstractXmlApplicationContext;

/**
 * 应用程序上下文
 * @Author: daihuaiyu
 * @Date: 2021/6/28 16:29
 * @Description:
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String [] configurationLocation;

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String[] configurationLocation) {
        this.configurationLocation = configurationLocation;
        refresh();
    }

    public ClassPathXmlApplicationContext(String configurationLocation){
        this(new String[]{configurationLocation});
    }

    @Override
    protected String[] getConfigurationLocations() {
        return configurationLocation;
    }
}
