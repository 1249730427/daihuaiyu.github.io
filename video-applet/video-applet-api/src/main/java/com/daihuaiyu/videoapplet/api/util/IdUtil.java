package com.daihuaiyu.videoapplet.api.util;

import java.util.Random;

/**
 * ID生成工具类
 *
 * @author daihuaiyu
 * @create: 2021-04-15 15:23
 **/
public class IdUtil {

    public static synchronized   String getId(){
        long t = System.currentTimeMillis();
        Random r=new Random(10);
        int i = r.nextInt()+10;
        return String.valueOf(t+i);
    }
}

