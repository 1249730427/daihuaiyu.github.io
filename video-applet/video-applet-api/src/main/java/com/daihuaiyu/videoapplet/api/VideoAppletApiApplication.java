package com.daihuaiyu.videoapplet.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.daihuaiyu.videoapplet.api.*","com.daihuaiyu.videoapplet.core.*"})
public class VideoAppletApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoAppletApiApplication.class, args);
    }

}
