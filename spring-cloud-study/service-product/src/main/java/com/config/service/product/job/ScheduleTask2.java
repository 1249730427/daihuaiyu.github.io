package com.config.service.product.job;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScheduleTask2
 * 采用cron表达式触发的定时任务
 */
@Component
public class ScheduleTask2 {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd HH24:mm:ss");

    @Scheduled(cron = "*/6 5 10 * * ?")
    @Async
    void process(){
        System.out.println("this is scheduler task running "+Thread.currentThread().getId()+""+format.format(new Date()));

    }
}
