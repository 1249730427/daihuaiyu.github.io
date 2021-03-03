package com.service.product.job;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask1 {

    private int count;

    /**
     * springBoot自带定时任务
     * 设置定时时间为6s触发一次
     */
    @Scheduled(fixedRate = 6000000)
    @Async
    void process(){
        System.out.println("this is scheduler task running " + Thread.currentThread().getId()+""+(count++));
    }


}
