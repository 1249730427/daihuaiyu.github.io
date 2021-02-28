package aop;

import service.HelloService;
import service.ICacheAdapter;
import sun.misc.ClassLoaderUtil;
import util.ClassLoaderUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK代理类
 *
 * @author daihuaiyu
 * @create: 2021-01-21 14:28
 **/
public class JDKProxy  {

    public static <T> T getProxy( Class<?>[] interfaces, HelloService helloService){
        InvocationHandler invocationHandler = new HelloServiceHandler(helloService);
//        ClassLoader classLoader = invocationHandler.getClass().getClassLoader();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return (T) Proxy.newProxyInstance(classLoader,interfaces,invocationHandler);
    }

    public static <T> T getProxy(Class<T> interFace, ICacheAdapter iCacheAdapter){

        InvocationHandler invocationHandler = (proxy, method, args) -> ICacheAdapter.class.getMethod(method.getName(), ClassLoaderUtils.getClazzByArgs(args)).invoke(iCacheAdapter,args);

        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[] {interFace},
                invocationHandler);
    }
}

