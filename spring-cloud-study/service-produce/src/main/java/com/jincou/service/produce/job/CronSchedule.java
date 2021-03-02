package com.jincou.service.produce.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CronSchedule {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void startScheduler() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public int  add(){
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("name","daihuaiyu");
            map.put("age",27);
            JobDetail cronJobDetail = JobBuilder.newJob(DemoJob2.class).withIdentity("test1","job1").build();
            cronJobDetail.getJobDataMap().putAll(map);
            // 定义调度触发规则
            // 使用cornTrigger规则
            // 触发器key
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("test1","job1")
                    .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/8 * * * * ?")).startNow().build();
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(cronJobDetail, trigger);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }

    }
}
