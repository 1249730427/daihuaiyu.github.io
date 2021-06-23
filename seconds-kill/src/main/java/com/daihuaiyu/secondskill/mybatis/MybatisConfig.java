package com.daihuaiyu.secondskill.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 *
 * @Author: hydai
 * @Date: 2021/6/22 07:56
 * @Description:
 */
@MapperScan("com.daihuaiyu.secondskill.dao")
@Configuration
public class MybatisConfig {

    /**
     * 添加MyBatis分页拦截器
     * @return
     */
    @Bean
    public PagerInterceptor pagerInterceptor(){
        return new PagerInterceptor();
    }
}
