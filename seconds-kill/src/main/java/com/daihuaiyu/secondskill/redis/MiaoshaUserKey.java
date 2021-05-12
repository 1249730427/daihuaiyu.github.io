package com.daihuaiyu.secondskill.redis;

/**
 * 秒杀用户key类
 *
 * @author daihuaiyu
 * @create: 2021-05-11 15:15
 **/
public class MiaoshaUserKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 3600*24 * 2;

    public MiaoshaUserKey(Integer seconds,String prefix) {
        super(seconds,prefix);
    }

    public static MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXPIRE, "tk");
}

