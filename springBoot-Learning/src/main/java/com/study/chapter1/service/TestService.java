package com.study.chapter1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author :daihuaiyu
 * @Description: 测试Service
 * @create 2021/2/5 22:09
 */
@Service
public class TestService {

    @Autowired
    @Qualifier("jtaPrimaryJdbcTemplate")
    private JdbcTemplate jtaPrimaryJdbcTemplate;

    @Autowired
    @Qualifier("jtaSecondaryjdbcTemplate")
    private JdbcTemplate jtaSecondaryJdbcTemplate;


    @Transactional
    public void tx(){
        // 修改test1库中的数据
        jtaPrimaryJdbcTemplate.update("update user set age = ? where name = ?", 30, "aaa");
        // 修改test2库中的数据
        jtaSecondaryJdbcTemplate.update("update user set age = ? where name = ?", 30, "aaa");

    }

    @Transactional
    public void tx2(){
        // 修改test1库中的数据
        jtaPrimaryJdbcTemplate.update("update user set age = ? where name = ?", 40, "aaa");
        throw new RuntimeException();
    }


}
