package com.study.chapter1.controller;

import com.study.chapter1.domain.UserVo;
import com.study.chapter1.entity.User;
import com.study.chapter1.mapper.primary.UserVoMapper;
import com.study.chapter1.mapper.secondary.UserMapper;
import com.study.chapter1.service.primary.UserRepository;
import com.study.chapter1.service.secondary.UserSecondaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试JPA
 *
 * @author daihuaiyu
 * @create: 2021-02-04 11:18
 **/
@RestController
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSecondaryRepository userSecondaryRepository;

//    @Autowired
//    private UserVoMapper userVoMapper;

    @Autowired
    private UserMapper userMapper;


    @RequestMapping(value="/test")
    public String test(){
        userRepository.save(new UserVo("aaa", 10));
        userRepository.save(new UserVo("bbb", 20));
        userRepository.save(new UserVo("ccc", 30));
        userRepository.save(new UserVo("ddd", 40));
        userRepository.save(new UserVo("eee", 50));
        System.out.println("Hello World");
        return "Hello World";
    }
    @RequestMapping(value="/test/secondary")
    @Transactional(value="sencondaryTranctionManager",rollbackFor = Exception.class)
    public String testSecondary(){
        userSecondaryRepository.save(new User("aaa", 10));
        userSecondaryRepository.save(new User("bbb", 20));
        userSecondaryRepository.save(new User("ccc", 30));
        userSecondaryRepository.save(new User("ddd", 40));
        userSecondaryRepository.save(new User("eee", 50));
        return "Hello Secondary World";
    }

//    @RequestMapping("/test/mybatis")
//    public String testMybatis(){
//        List<UserVo> userVo = userVoMapper.selectByName("dy");
//        userVo.stream().forEach(userVo1 -> System.out.println(userVo1.getAge()));
//        return "Hello Mybatis";
//    }

    @RequestMapping("/test/mybatis2")
    public String testMybatis2(){
        List<User> userVo = userMapper.selectByUserName("aaa");
        userVo.stream().forEach(userVo1 -> System.out.println(userVo1.getAge()));
        return "Hello Mybatis";
    }

}

