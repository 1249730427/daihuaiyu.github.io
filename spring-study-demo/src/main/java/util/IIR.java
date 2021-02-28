package util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author :daihuaiyu
 * @Description: 模拟Redis集群IIR
 * @create 2021/2/28 9:27
 */
@Slf4j
public class IIR {

    private ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();

    public String gain(String key){

        log.info("Redis集群IIR获取数据key :{}", key);

        return concurrentHashMap.get(key);
    }
    public void set(String key ,String value){

        log.info("Redis集群IIR设置数据key :{} value：{}", key,value);

        concurrentHashMap.put(key,value);
    }

    public void setExpire(String key , String value, Long timeOut, TimeUnit timeUnit){

        log.info("Redis集群IIR设置数据key :{} value：{} timeOut ：{} timeUnit ：{}", key,value,timeOut,timeUnit);

        concurrentHashMap.put(key,value);
    }

    public void del(String key){

        log.info("Redis集群IIR删除数据key :{}", key);

        concurrentHashMap.remove(key);
    }
}
