package com.study.chapter1.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * @author :daihuaiyu
 * @Description: JTA接入多数据源
 * @create 2021/2/5 21:50
 */
@Configuration
public class JtaDataSourceConfiguration {

    @Bean(name = "atomikosTransactionManager")
    public TransactionManager atomikosTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean(name = "txManager")
    @DependsOn({ "userTransaction", "atomikosTransactionManager" })
    public PlatformTransactionManager transactionManager() throws Throwable {
        UserTransaction userTransaction = userTransaction();
        TransactionManager atomikosTransactionManager = atomikosTransactionManager();
        return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
    }

    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.primary")
    @Primary
    @DependsOn({"txManager"})
    public DataSource jtaPrimaryDataSource(){

        return new AtomikosDataSourceBean();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.secondary")
    @DependsOn({"txManager"})
    public DataSource jtaSecondaryDataSource(){

        return new AtomikosDataSourceBean();
    }

    @Bean(name="jtaPrimaryJdbcTemplate")
    public JdbcTemplate jtaPrimaryJdbcTemplate(@Qualifier("jtaPrimaryDataSource")DataSource jtaPrimaryDataSource){

        return new JdbcTemplate(jtaPrimaryDataSource);
    }

    @Bean(name = "jtaSecondaryjdbcTemplate")
    public JdbcTemplate jtaSecondaryjdbcTemplate(@Qualifier("jtaSecondaryDataSource")DataSource jtaSecondaryDataSource){

        return new JdbcTemplate(jtaSecondaryDataSource);
    }

}
