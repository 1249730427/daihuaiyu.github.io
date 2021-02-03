package com.study.chapter1;

import com.study.chapter1.domain.UserVo;
import com.study.chapter1.service.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringbootLearningApplicationTests {

    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void queryPrimaryUser(){
        List<Map<String, Object>> list = primaryJdbcTemplate.queryForList("select * from user ");

        list.stream().forEach(lists-> System.out.println(lists.get("username")));

    }

    @Test
    void insertPrimaryUser(){
        primaryJdbcTemplate.execute("insert into user(username,age) values ('lisi',30)");
        throw new RuntimeException();
    }

    @Test
    void save(){
        userRepository.save(new UserVo("daiyu",30));
    }



}
