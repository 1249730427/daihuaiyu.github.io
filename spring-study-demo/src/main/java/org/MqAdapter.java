package org;

import com.alibaba.fastjson.JSON;
import config.RebateInfo;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * MQ消息适配器
 *
 * @author daihuaiyu
 * @create: 2021-02-25 17:18
 **/
public class MqAdapter {

    public static RebateInfo filter(String strJson, Map<String,String> link) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        return filter(JSON.parseObject(strJson,Map.class),link);
    }

    private static <T> RebateInfo filter(Map<String,Object> data,Map<String,String> link) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RebateInfo rebateInfo = new RebateInfo();
        for(String key:link.keySet()){
            Object val = data.get(link.get(key));
            //利用反射给RebateInfo设置值
            RebateInfo.class.getMethod("set"+key.substring(0,1).toUpperCase()+key.substring(1), String.class).invoke(rebateInfo,val.toString());
        }
        return rebateInfo;
    }
}

