package com.study.chapter1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author :daihuaiyu
 * @Description: 主数据源JPA配置
 * @create 2021/2/3 21:23
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactoryPrimary",
        transactionManagerRef="transactionManagerPrimary",
        basePackages= { "com.study.chapter1.domain" })
public class PrimaryConfig {

    @Autowired
    @Qualifier(value = "primaryDataSource")
    private DataSource primaryDataSource;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Autowired
    private JpaProperties jpaProperties;

    @Bean(name = "entityManager")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){

        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryPrimary")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder){

        return builder.dataSource(primaryDataSource).packages("com.study.chapter1.domain") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }

    private Map<String,Object> getVendorProperties() {

        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    @Bean(name="transactionManagerPrimary")
    @Primary
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());

    }
}
