package org;

import dao.IUserDAO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;

/**
 * Bean注册
 *
 * @author daihuaiyu
 * @create: 2021-02-24 13:58
 **/
public class RegisterBeanFactory implements BeanDefinitionRegistryPostProcessor {
    /**
     * 将代理对象注入到Spring容器中
     * @param beanDefinitionRegistry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(MapperFactoryBean.class);
        genericBeanDefinition.setScope("singleton");
        genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(IUserDAO.class);
        BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(genericBeanDefinition, "userDao");
        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder,beanDefinitionRegistry);
    }

    /**
     *
     * @param configurableListableBeanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}

