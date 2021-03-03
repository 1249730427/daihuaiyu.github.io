package com.service.product.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 自定义job任务
 * DemoJob
 */
public class DemoJob extends QuartzJobBean {

    String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Demo:"+"Hello "+name);
    }
}
