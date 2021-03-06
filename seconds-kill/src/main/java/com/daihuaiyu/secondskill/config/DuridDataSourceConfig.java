package com.daihuaiyu.secondskill.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Durid数据库连接池的配置
 * @ClassName: DuridDataSourceConfig
 * @Author: hydai
 * @Date: 2021/5/9 06:41
 * @Description:
 */
@Configuration
@MapperScan(basePackages = {"com.daihuaiyu.secondskill.dao"},sqlSessionTemplateRef="sqlSessionTemplate")
public class DuridDataSourceConfig {

    //构建数据源
    @Bean
    @ConfigurationProperties(prefix ="spring.datasource.druid" )
    public DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    //构建SqlSessionFactory
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    //构建SqlSessionTemplate
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //构建数据源事务管理器
    @Bean
    public PlatformTransactionManager txManage(@Qualifier("dataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
