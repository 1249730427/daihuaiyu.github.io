
import aop.HelloServiceHandler;
import aop.JDKProxy;
import service.HelloService;
import service.impl.HelloServiceImpl;
import service.impl.SayServiceImpl;

import java.lang.reflect.Proxy;


/**
 * 主类
 *
 * @author daihuaiyu
 * @create: 2021-01-21 14:37
 **/
public class HelloClient {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();

        HelloService proxyInstance = JDKProxy.getProxy(helloService.getClass().getInterfaces(), new HelloServiceImpl());

//        HelloService helloService = new HelloServiceImpl();
//
//        HelloServiceHandler helloServiceHandler = new HelloServiceHandler(helloService);
////
//        HelloService proxyInstance = (HelloService) Proxy.newProxyInstance(helloServiceHandler.getClass().getClassLoader(), helloService.getClass().getInterfaces(), helloServiceHandler);
//
          proxyInstance.hello("大家好！！");

        HelloService sayService  = new SayServiceImpl();
//
//        HelloServiceHandler sayServiceHandler = new HelloServiceHandler(sayService);
//
//        HelloService sayProxyInstance = (HelloService) Proxy.newProxyInstance(helloServiceHandler.getClass().getClassLoader(), helloService.getClass().getInterfaces(), sayServiceHandler);

        HelloService sayProxyInstance = JDKProxy.getProxy(sayService.getClass().getInterfaces(), new SayServiceImpl());

        sayProxyInstance.hello("大家好！！");
    }
}

