package com.daihuaiyu.springframework;

import cn.hutool.core.io.IoUtil;
import com.daihuaiyu.springframework.aop.AdvisedSupport;
import com.daihuaiyu.springframework.aop.TargetSource;
import com.daihuaiyu.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.daihuaiyu.springframework.aop.framework.AopProxy;
import com.daihuaiyu.springframework.aop.framework.Cglib2AopProxy;
import com.daihuaiyu.springframework.aop.framework.JdkDynamicAopProxy;
import com.daihuaiyu.springframework.bean.*;
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
import com.daihuaiyu.springframework.event.CustomEvent;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

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

    @Test
    public void test_aware(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        classPathXmlApplicationContext.registerShutdownHook();
        UserService userService = (UserService) classPathXmlApplicationContext.getBean("userService");
        userService.queryUserInfo();
//        System.out.println("ApplicationContextAware："+userService.getApplicationContext());
//        System.out.println("BeanFactoryAware："+userService.getBeanFactory());
    }

    @Test
    public void test_scope(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        classPathXmlApplicationContext.registerShutdownHook();
        // 2. 获取Bean对象调用方法
        UserService userService01 = (UserService) classPathXmlApplicationContext.getBean("userService", UserService.class);
        UserService userService02 = (UserService) classPathXmlApplicationContext.getBean("userService", UserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
    }

    @Test
    public void test_factory_bean(){
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        applicationContext.registerShutdownHook();

        // 2. 调用代理方法
        UserService userService = (UserService) applicationContext.getBean("userService", UserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.daihuaiyu.springframework.bean.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void test_dynamic(){
        IUserService userService = new UserService();
        AdvisedSupport advised = new AdvisedSupport();
        advised.setTargetSource(new TargetSource(userService));
        advised.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.daihuaiyu.springframework.bean.IUserService.*(..))"));
        advised.setMethodInterceptor(new UserServiceInterceptor());
        AopProxy aopProxy = new JdkDynamicAopProxy(advised);
         IUserService proxy = (IUserService) aopProxy.getProxy();
        System.out.println(proxy.queryUserInfo());
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advised).getProxy();
        System.out.println(proxy_cglib.register("带鱼"));
    }

    @Test
    public void test_aop2(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService",IUserService.class);
        System.out.println("测试结果："+userService.queryUserInfo());
    }

}
