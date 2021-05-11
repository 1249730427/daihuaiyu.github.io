package com.daihuaiyu.secondskill.controller;

import com.daihuaiyu.secondskill.config.CodeEnum;
import com.daihuaiyu.secondskill.exception.GlobalException;
import com.daihuaiyu.secondskill.service.MiaoshaUserService;
import com.daihuaiyu.secondskill.util.Result;
import com.daihuaiyu.secondskill.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

/**
 * 登录Controller
 *
 * @author daihuaiyu
 * @create: 2021-05-10 16:19
 **/
@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin( HttpServletResponse response, @Valid LoginVo loginVo){
        logger.info("登录信息:"+loginVo.toString());
        miaoshaUserService.login(response, loginVo);
        String token = UUID.randomUUID().toString();
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
//        opsForValue.set(token,);
        return Result.success(Boolean.TRUE);
    }
}

