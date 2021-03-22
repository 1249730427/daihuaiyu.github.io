package com.service.product.job;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
@EnableAsync
public class AsyncConfig {

    private int corePoolSize = 10;
    private int maxPoolSize = 20;
    private int queueCapacity = 100;

    @Bean
    public Executor taskExecutor() {

        ThreadPoolTaskExecutor excutor = new ThreadPoolTaskExecutor();
        excutor.setCorePoolSize(corePoolSize);
        excutor.setMaxPoolSize(maxPoolSize);
        excutor.setQueueCapacity(queueCapacity);
        excutor.initialize();
        return excutor;

    }

}
