package com.daihuaiyu.secondskill.redis;

/**
 * 过期时间设置和获取key的抽象类
 *
 * @author daihuaiyu
 * @create: 2021-05-11 14:27
 **/
public abstract class BasePrefix implements KeyPrefix {

    private Integer  expireSeconds;

    private String prefix;

    public BasePrefix(String prefix){
        this(0,prefix);
    }

    public BasePrefix(Integer expireSeconds,String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }
    /**
     * 过期时间
     */
    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    /**
     * 获取前缀
     */
    @Override
    public String getPrefix() {
        String simpleName = getClass().getSimpleName();
        prefix =simpleName+":"+prefix;
        return prefix;
    }
}

