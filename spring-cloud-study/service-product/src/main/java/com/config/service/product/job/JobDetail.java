package com.config.service.product.job;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 新建一个JobDetail
 * JobDetail
 */
@Configuration
class SampleScheduler {

    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(DemoJob.class).withIdentity("job","test").
                usingJobData("name","World !").storeDurably().build();
    }

    @Bean
    public Trigger sampleTrigger(){
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10000).repeatForever();
        return TriggerBuilder.newTrigger().forJob(sampleJobDetail()).withIdentity("sampleTrigger").withSchedule(builder).build();
    }

}
