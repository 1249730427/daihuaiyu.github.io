package com.jincou.service.produce.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Async
public class DemoJob2 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map=jobExecutionContext.getJobDetail().getJobDataMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
        System.out.println("当前时间["+format.format(new Date())+"]，"+"线程ID["+Thread.currentThread().getId()+"]"+"的值为:"+map.get("name"));
    }
}
