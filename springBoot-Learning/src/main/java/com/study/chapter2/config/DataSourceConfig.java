package com.study.chapter2.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 读写分离数据库配置
 *
 * @ClassName: DataSourceConfig
 * @Author: hydai
 * @Date: 2021/5/5 06:34
 * @Description:
 */
@Configuration
@MapperScan(value = {"com.study.chapter2.mapper"},sqlSessionTemplateRef="sqlSessionTemplate",sqlSessionFactoryRef="sqlSessionFactory")
public class DataSourceConfig {

    /**主数据源*/
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource(){
        return DruidDataSourceBuilder.create().build();
    };

    /**从数据源*/
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource salveDataSource(){
        return DruidDataSourceBuilder.create().build();
    };

    /**动态路由*/
    @Bean
    public DataSourceRouter dynamicDB(@Qualifier(value="masterDataSource")DataSource masterDataSource,
                                      @Autowired(required = false) @Qualifier(value="salveDataSource") DataSource salveDataSource){
        DataSourceRouter dataSourceRouter = new DataSourceRouter();
        Map<Object,Object> targetDataSources =new HashMap<>();
        targetDataSources.put(DataSourceEnum.MASTER.getSourceName(),masterDataSource);
        if(salveDataSource!=null){
            targetDataSources.put(DataSourceEnum.SLAVER.getSourceName(),salveDataSource);
        }
        dataSourceRouter.setTargetDataSources(targetDataSources);
        dataSourceRouter.setDefaultTargetDataSource(masterDataSource);
        return dataSourceRouter;
    };
    /**构建SessionFactory*/
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "dynamicDB")DataSourceRouter dynamicDB ) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*Mapper.xml"));
        sqlSessionFactoryBean.setDataSource(dynamicDB);
        return sqlSessionFactoryBean.getObject();
    }
    /**构建SqlSessionTemplate*/
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(value = "sqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**构建DataSourceTransactionManager*/
    @Bean(value="dataSourceTx")
    public DataSourceTransactionManager transactionManager(@Qualifier(value = "dynamicDB")DataSourceRouter dynamicDB ){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dynamicDB);
        return transactionManager;
    }

}
