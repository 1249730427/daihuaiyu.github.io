package util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author :daihuaiyu
 * @Description: 模拟Redis集群EGM
 * @create 2021/2/28 9:27
 */
@Slf4j
public class EGM {

    private ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();

    public String gain(String key){

        log.info("Redis集群EGM获取数据key :{}", key);

        return concurrentHashMap.get(key);
    }
    public void set(String key ,String value){

        log.info("Redis集群EGM设置数据key :{} value：{}", key,value);

        concurrentHashMap.put(key,value);
    }

    public void setEx(String key , String value, Long timeOut, TimeUnit timeUnit){

        log.info("Redis集群EGM设置数据key :{} value：{} timeOut ：{} timeUnit ：{}", key,value,timeOut,timeUnit);

        concurrentHashMap.put(key,value);
    }

    public void delete(String key){

        log.info("Redis集群EGM删除数据key :{}", key);

        concurrentHashMap.remove(key);
    }
}
