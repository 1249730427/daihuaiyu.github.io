package org.com.daihuaiyu.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: daihuaiyu
 * @Date: 2021/7/26 18:04
 * @Description:
 */
public class ApiTest {

    @Test
    public void test_rpc(){
        String[] configs = {"rpc-consumer.xml", "rpc-provider.xml"};
        new ClassPathXmlApplicationContext(configs);
    }
}
