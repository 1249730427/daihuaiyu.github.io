package com.daihuaiyu.secondskill.util;

/**
 * ThreadLocal工具类
 *
 * @author daihuaiyu
 * @create: 2021-06-01 14:17
 **/
public class ThreadLocalUtil {

    private static ThreadLocal<Object> threadLocal = new ThreadLocal();

    public static void setThreadLocal(Object obj){
        threadLocal.set(obj);
    }

    public static Object getTheadLocal(){

        return threadLocal.get();
    }

    public static void removeThreadLocal(){
        threadLocal.remove();
    }
}

