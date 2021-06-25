package com.daihuaiyu.springframework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试类UserDao
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/25 16:12
 * @Description:
 */
public class UserDao {

    private static final Map<String,Object> userMap = new HashMap<>();

    static {
        userMap.put("daihuaiyu","戴怀玉");
        userMap.put("zhangsan","张三");
        userMap.put("lisi","李四");
    }
    public Object queryUserName(String name){
        return userMap.get(name);
    }
}
