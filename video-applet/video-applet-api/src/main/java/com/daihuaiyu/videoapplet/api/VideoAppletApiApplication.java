package com.daihuaiyu.videoapplet.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
//@ComponentScan(value = {"com.daihuaiyu.videoapplet.api.*"})
@EnableJpaRepositories(basePackages ={"com.daihuaiyu.videoapplet.core.dao"} )
@EntityScan(basePackages = {"com.daihuaiyu.videoapplet.core.domain"})
//@EnableTransactionManagement
@SpringBootApplication
public class VideoAppletApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoAppletApiApplication.class, args);
    }

}
