package controller;

import domain.Activity;
import domain.Stock;
import org.ActivityFactory;
import util.RedisUtil;

/**
 * 活动controller
 *
 * @author daihuaiyu
 * @create: 2021-03-02 13:49
 **/
public class ActivityController {

    private RedisUtil redisUtil = new RedisUtil();

    public  Activity queryActivityInfo(Long activityId){
        Activity activity = ActivityFactory.getActivity(activityId);
        // 模拟从Redis中获取库存变化信息
        Stock stock = new Stock(1000, redisUtil.getStockUsed());
        activity.setStock(stock);

        return activity;
    }
}

