package com.daihuaiyu.secondskill.controller;

import com.daihuaiyu.secondskill.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HelloDemo
 * @Author: hydai
 * @Date: 2021/5/9 07:15
 * @Description:
 */
@Controller
public class HelloDemo {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping("hi")
    public String say(Model model){
        model.addAttribute("name","张三");
        return "hello";
    }
    @RequestMapping("setRedis")
    @ResponseBody
    public Result<String> set(){
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("key1","value1");
        return Result.success(opsForValue.get("key1"));
    }

}
