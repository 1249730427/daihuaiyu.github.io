package com.study.chapter1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * JPA接入多数据源
 *
 * @author daihuaiyu
 * @create: 2021-02-04 11:43
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef="tranctionManagerSecondary",
    transactionManagerRef = "sencondaryTranctionManager",
    basePackages = {"com.study.chapter1.entity","com.study.chapter1.service.secondary" })
public class SecondaryConfig {

    @Autowired
    @Qualifier("jpaSecondaryDataSource")
    private DataSource secondaryDataSource;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Autowired
    private JpaProperties jpaProperties;

    @Bean(name="secondayEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        return tranctionManagerSecondary(builder).getObject().createEntityManager();
    }

    @Bean(name="tranctionManagerSecondary")
    public LocalContainerEntityManagerFactoryBean tranctionManagerSecondary(EntityManagerFactoryBuilder builder){
        return builder.dataSource(secondaryDataSource)
                .persistenceUnit("persistenceUnit")
                .packages("com.study.chapter1.entity")
                .properties(getVersionProperties())
                .build();
    }

    private Map<String, ?> getVersionProperties() {

        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    @Bean(name="sencondaryTranctionManager")
    public PlatformTransactionManager sencondaryTranctionManager(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(tranctionManagerSecondary(builder).getObject());
    }


}

