package com.daihuaiyu.springframework;

import cn.hutool.core.io.IoUtil;
import com.daihuaiyu.springframework.bean.MyBeanFactoryPostProcessor;
import com.daihuaiyu.springframework.bean.MyBeanPostProcessor;
import com.daihuaiyu.springframework.bean.UserDao;
import com.daihuaiyu.springframework.bean.UserService;
import com.daihuaiyu.springframework.beans.factory.BeanFactory;
import com.daihuaiyu.springframework.beans.factory.BeanReference;
import com.daihuaiyu.springframework.beans.factory.PropertyValue;
import com.daihuaiyu.springframework.beans.factory.PropertyValues;
import com.daihuaiyu.springframework.beans.factory.factory.BeanDefinition;
import com.daihuaiyu.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.daihuaiyu.springframework.beans.factory.support.XmlBeanDefinitionReader;
import com.daihuaiyu.springframework.context.support.ClassPathXmlApplicationContext;
import com.daihuaiyu.springframework.core.io.DefaultResourceLoader;
import com.daihuaiyu.springframework.core.io.Resource;
import com.daihuaiyu.springframework.core.io.ResourceLoader;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;

/**
 * 方法测试类
 * @Author: daihuaiyu
 * @Date: 2021/6/24 16:49
 * @Description:
 */
public class ApiTest {

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }

//    @Test
//    public void test_BeanFactory() {
//        BeanFactory beanFactory = new BeanFactory();
//        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
//        beanFactory.registerBeanDefinition("userService",beanDefinition);
//        UserService userService = (UserService) beanFactory.getBean("userService");
//        userService.queryUserInfo();
//    }

    @Test
    public void testBeanFactory(){
        //1.初始化Bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //2.注册Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",beanDefinition);
        //3.获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService","带鱼");
        //4.调用对象方法
        userService.queryUserInfo();
        //5.获取单例对象
        userService = (UserService) beanFactory.getBean("userService","带鱼");
        //6.调用对象方法
        userService.queryUserInfo();
    }

    //无构造函数实例化
    @Test
    public void test_newInstance() throws IllegalAccessException, InstantiationException {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }

    //有构造函数实例化
    @Test
    public void test_constructor() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("带鱼");
        System.out.println(userService);
    }

    //获取构造函数信息
    @Test
    public void test_parameterTypes() throws Exception {
        Class<UserService> beanClass = UserService.class;
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor<?> constructor = declaredConstructors[0];
        Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
        UserService userService = declaredConstructor.newInstance("带鱼");
        System.out.println(userService);
    }

    @Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object obj = enhancer.create(new Class[]{String.class}, new Object[]{"带鱼"});
        System.out.println(obj);
    }

    @Test
    public void testBeanFactory2(){
        //1.初始化Bean工厂
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
        //2.注册Bean，并进行属性填充
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userName","daihuaiyu"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class,propertyValues);
        defaultListableBeanFactory.registerBeanDefinition("userService",beanDefinition);
        //3.获取Bean
        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/1249730427/daihuaiyu.github.io/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:application.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_processor() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:application.xml");

        MyBeanFactoryPostProcessor myBeanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        myBeanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);

        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 3. 获取Bean对象调用方法
        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
    @Test
    public void test_classPath() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        UserService userService = (UserService) classPathXmlApplicationContext.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void test_initializingBeanAndDisposableBean(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        classPathXmlApplicationContext.registerShutdownHook();
        UserService userService = (UserService) classPathXmlApplicationContext.getBean("userService");
        userService.queryUserInfo();
    }

}
