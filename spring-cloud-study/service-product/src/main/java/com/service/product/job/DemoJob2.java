package com.service.product.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
@Async
public class DemoJob2 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
    }
}
