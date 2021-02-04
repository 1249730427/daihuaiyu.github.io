package com.study.chapter1.controller;

import com.study.chapter1.domain.UserVo;
import com.study.chapter1.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

