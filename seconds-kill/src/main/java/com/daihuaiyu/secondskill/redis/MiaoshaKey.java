package com.daihuaiyu.secondskill.redis;

/**
 * 秒杀key
 *
 * @author daihuaiyu
 * @create: 2021-05-26 17:18
 **/
public class MiaoshaKey extends BasePrefix {

    public MiaoshaKey(String prefix) {
        super(prefix);
    }
    public static MiaoshaKey isGoodsOver = new MiaoshaKey("go");
}

