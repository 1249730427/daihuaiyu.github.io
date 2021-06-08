package com.daihuaiyu.secondskill.controller;

import com.daihuaiyu.secondskill.config.CodeEnum;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.service.MiaoshaUserService;
import com.daihuaiyu.secondskill.util.Result;
import com.daihuaiyu.secondskill.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 登录Controller
 *
 * @author daihuaiyu
 * @create: 2021-05-10 16:19
 **/
@Controller
@RequestMapping("/login")
@Api("登录相关API")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/do_login",method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "登录页面")
    public Result<Boolean> doLogin( HttpServletResponse response, @Valid LoginVo loginVo){
        logger.info("登录信息:"+loginVo.toString());
        miaoshaUserService.login(response, loginVo);
        return Result.success(Boolean.TRUE);
    }

    @RequestMapping(value = "/query_user",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "登录")
    public Result<MiaoshaUser> queryMiaoshaUser(MiaoshaUser miaoshaUser){
        if(miaoshaUser ==null){
            return Result.error(CodeEnum.LOGIN_FRIST);
        }
        return Result.success(miaoshaUser);
    }

}

