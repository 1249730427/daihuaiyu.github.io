package com.daihuaiyu.secondskill.redis;

/**
 * redis中前缀key接口
 *
 * @author daihuaiyu
 * @create: 2021-05-11 14:24
 **/
public interface KeyPrefix {

    /**过期时间*/
     int expireSeconds();

     /**获取前缀*/
     String getPrefix();


}
