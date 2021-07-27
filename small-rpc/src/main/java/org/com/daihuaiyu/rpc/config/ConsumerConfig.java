package org.com.daihuaiyu.rpc.config;

/**
 * 消费者配置
 * @Author: daihuaiyu
 * @Date: 2021/7/26 17:04
 * @Description:
 */
public class ConsumerConfig<T> {

    private String nozzle; //接口

    private String alias; //别名

    protected synchronized T refer(){
        System.out.format("消费者信息=> [接口：%s] [别名：%alias]",nozzle,alias);
        return null;
    }
}
