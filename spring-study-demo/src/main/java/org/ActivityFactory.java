package org;

import domain.Activity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 *
 * @author daihuaiyu
 * @create: 2021-03-02 13:28
 **/
public class ActivityFactory {

    private static Map<Long, Activity> activityMap = new HashMap<>();

    public static Activity getActivity(Long activityId){
        Activity activity = activityMap.get(activityId);
        if(activity==null){
            // 模拟从实际业务应用从接口中获取活动信息
            activity = new Activity();
            activity.setId(10001L);
            activity.setName("图书嗨乐");
            activity.setDesc("图书优惠券分享激励分享活动第二期");
            activity.setStartTime(new Date());
            activity.setStopTime(new Date());
            activityMap.put(activityId,activity);
        }
        return activity;
    }
}

