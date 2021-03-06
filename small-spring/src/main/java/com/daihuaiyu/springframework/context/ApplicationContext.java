package com.daihuaiyu.springframework.context;

import com.daihuaiyu.springframework.ApplicationEventPublisher;
import com.daihuaiyu.springframework.beans.factory.factory.HierarchicalBeanFactory;
import com.daihuaiyu.springframework.beans.factory.factory.ListableBeanFactory;
import com.daihuaiyu.springframework.core.io.ResourceLoader;

/**
 * Spring应用上下文
 * @Author: daihuaiyu
 * @Date: 2021/6/27 09:34
 * @Description:
 */
public interface ApplicationContext  extends ListableBeanFactory , HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
