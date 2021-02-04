package com.study.chapter1.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author :daihuaiyu
 * @Description: Jpa接入多数据源
 * @create 2021/2/3 21:04
 */
@Configuration
public class JpaDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.jpa.primary")
    public DataSource jpaPrimaryDataSource(){

        return DataSourceBuilder.create().build();
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.jpa.secondary")
    public DataSource jpaSecondaryDataSource(){

        return DataSourceBuilder.create().build();
    }





}
