package com.daihuaiyu.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.daihuaiyu.springframework.beans.BeansException;
import com.daihuaiyu.springframework.beans.factory.BeanReference;
import com.daihuaiyu.springframework.beans.factory.PropertyValue;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;
import com.daihuaiyu.springframework.core.io.DefaultResourceLoader;
import com.daihuaiyu.springframework.core.io.Resource;
import com.daihuaiyu.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName: XmlBeanDefinitionReader
 * @Author: hydai
 * @Date: 2021/6/26 09:34
 * @Description:
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    /**
     * 根据单个Resource加载BeanDefinition
     *
     * @param resource
     */
    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()){
                //拿到输入流，解析XML文件
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException  | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
         Document document = XmlUtil.readXML(inputStream); //从输入流拿到document
         Element element = document.getDocumentElement(); //获取element
         NodeList childNodes = element.getChildNodes();  //获取所有子节点
        for(int i=0;i<childNodes.getLength();i++){
            Node node = childNodes.item(i);
            if(! (node instanceof Element)){ continue; } //判断元素
            if(!"bean".equals(node.getNodeName())){ continue;} //判断是否是Bean节点
            //解析XML
            Element bean = (Element) node;
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String aClass = bean.getAttribute("class");
            String initMethod = bean.getAttribute("init-method");
            String destroyMethod = bean.getAttribute("destroy-method");
            Class<?> clazz = Class.forName(aClass);
            //id优先级高于name
            String beanName = StrUtil.isEmpty(id)?name:id;
            if(StrUtil.isEmpty(beanName)){
                beanName =StrUtil.lowerFirst(clazz.getSimpleName());
            }
            BeanDefinition beanDefinition = new BeanDefinition(clazz,initMethod,destroyMethod);
            //解析Bean节点下的节点
            for(int j=0;j<bean.getChildNodes().getLength();j++){
                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                Object value = StrUtil.isNotEmpty(attrRef)?new BeanReference(attrRef):attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName,value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            //注册Bean
            getRegistry().registerBeanDefinition(beanName,beanDefinition);
        }
    }

    /**
     * 根据多个Resource加载BeanDefinition
     *
     * @param resources
     */
    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for(Resource resource:resources){
            loadBeanDefinitions(resource);
        }
    }

    /**
     * 根据文件位置加载BeanDefinition
     *
     * @param location
     */
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        final Resource resource = this.getResource().getResource(location);
        loadBeanDefinitions(resource);
    }
}
