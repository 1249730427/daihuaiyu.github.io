package com.study.chapter1.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * JdbcTemplate接入多数据源
 *
 * @author daihuaiyu
 * @create: 2021-02-03 13:46
 **/
@Configuration
public class JdbcTemplateDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource(){

        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate primaryJdbcTemplate(DataSource primaryDataSource){

        return new JdbcTemplate(primaryDataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource(){

        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate secondaryJdbcTemplate(DataSource secondaryDataSource){

        return new JdbcTemplate(secondaryDataSource);
    }

    @Bean(name="primaryTranction")
    public PlatformTransactionManager primaryTranction(DataSource primaryDataSource){

       return new JdbcTransactionManager(primaryDataSource);
    }


}

