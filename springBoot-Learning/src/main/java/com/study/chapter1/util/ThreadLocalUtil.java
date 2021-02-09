package com.study.chapter1.util;

import org.apache.log4j.helpers.ThreadLocalMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程上下文工具类
 *
 * @author daihuaiyu
 * @create: 2021-02-09 13:34
 **/
public class ThreadLocalUtil<T> {

    private static final ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal(){
        /**
         * Returns the current thread's "initial value" for this
         * thread-local variable.  This method will be invoked the first
         * time a thread accesses the variable with the {@link #get}
         * method, unless the thread previously invoked the {@link #set}
         * method, in which case the {@code initialValue} method will not
         * be invoked for the thread.  Normally, this method is invoked at
         * most once per thread, but it may be invoked again in case of
         * subsequent invocations of {@link #remove} followed by {@link #get}.
         *
         * <p>This implementation simply returns {@code null}; if the
         * programmer desires thread-local variables to have an initial
         * value other than {@code null}, {@code ThreadLocal} must be
         * subclassed, and this method overridden.  Typically, an
         * anonymous inner class will be used.
         *
         * @return the initial value for this thread-local
         */
        @Override
        protected Map<String,Object> initialValue() {

            return new HashMap<>();
        }
    };

    public static <T>  T getThreadLocal(String key){

        Map<String, Object> map = threadLocal.get();

        return (T) map.get(key);
    };

    public static void setThreadLocal(String key,Object o){
        Map<String, Object> map = threadLocal.get();
        map.put(key,o);
    }

    public static void removeThreadLocal(String key){
        threadLocal.remove();
    }
}

