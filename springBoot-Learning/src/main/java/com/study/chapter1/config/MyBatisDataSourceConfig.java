package com.study.chapter1.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * mybatis接入多数据源
 *
 * @author daihuaiyu
 * @create: 2021-02-05 14:24
 **/
@Configuration
@MapperScan(sqlSessionTemplateRef="sqlSessionTemplateSecondary",sqlSessionFactoryRef="sqlSessionFactorySecondary",
            basePackages = {"com.study.chapter1.mapper.secondary"})
public class MyBatisDataSourceConfig {

    @ConfigurationProperties(value="spring.datasource.mybatis.primary")
    @Bean
    public DataSource mybatisPrimarySource(){

        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(value="spring.datasource.mybatis.secondary")
    public DataSource mybatisSecondarySource(){

        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryPrimary(@Qualifier("mybatisPrimarySource") DataSource mybatisPrimarySource) throws Exception{

        SqlSessionFactoryBean  sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(mybatisPrimarySource);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplatePrimary (@Qualifier("sqlSessionFactoryPrimary") SqlSessionFactory sqlSessionFactory){

        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SqlSession sqlSessionPrimary(@Qualifier("sqlSessionFactoryPrimary") SqlSessionFactory sqlSessionFactory){
        return  sqlSessionFactory.openSession();
    }

    @Bean(name = "mybatisTransactionManagerPrimary")
    public PlatformTransactionManager transactionManagerPrimary(@Qualifier("mybatisPrimarySource") DataSource mybatisPrimarySource){

        return new DataSourceTransactionManager(mybatisPrimarySource);
    }


    @Bean
    public SqlSessionFactory sqlSessionFactorySecondary(@Qualifier(value = "mybatisSecondarySource") DataSource mybatisSecondarySource) throws Exception{

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(mybatisSecondarySource);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateSecondary(@Qualifier(value = "sqlSessionFactorySecondary") SqlSessionFactory sqlSessionFactorySecondary ){

        return new SqlSessionTemplate(sqlSessionFactorySecondary);
    }

    @Bean(name = "mybatisTransactionManagerSecondary")
    public PlatformTransactionManager transactionManagerSecondary(@Qualifier("mybatisSecondarySource") DataSource mybatisSecondarySource){

        return new DataSourceTransactionManager(mybatisSecondarySource);
    }
}

