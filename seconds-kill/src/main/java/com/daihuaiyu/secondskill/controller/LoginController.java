package com.daihuaiyu.secondskill.controller;

import com.daihuaiyu.secondskill.config.CodeEnum;
import com.daihuaiyu.secondskill.exception.GlobalException;
import com.daihuaiyu.secondskill.util.Result;
import com.daihuaiyu.secondskill.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 登录Controller
 *
 * @author daihuaiyu
 * @create: 2021-05-10 16:19
 **/
@Controller
@RequestMapping("/login")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/do_login")
    @ResponseBody
    public Result toLogin(HttpServletRequest request, @Valid LoginVo loginVo){
        logger.info("登录信息:"+loginVo.toString());
        if(loginVo ==null){
            throw  new GlobalException(CodeEnum.SERVER_ERROR);
        }
        //判断用户是否存在

        return null;
    }
}

