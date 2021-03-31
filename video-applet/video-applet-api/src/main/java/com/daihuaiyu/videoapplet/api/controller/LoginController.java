package com.daihuaiyu.videoapplet.api.controller;

import com.daihuaiyu.videoapplet.api.service.UserService;
import com.daihuaiyu.videoapplet.api.util.ApiResponse;
import com.daihuaiyu.videoapplet.api.util.MD5Util;
import com.daihuaiyu.videoapplet.api.util.RedisOperatorUtil;
import com.daihuaiyu.videoapplet.core.domain.UserVo;
import com.daihuaiyu.videoapplet.core.domain.Users;
import io.swagger.annotations.ApiOperation;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录Controller
 *
 * @author daihuaiyu
 * @create: 2021-03-31 10:15
 **/
@RestController
public class LoginController {

    private final static String USERSESSIONID="user-session-id";

    @Autowired
    private UserService userService;

    @Autowired
    private RedisOperatorUtil RedisOperator;
    /**
     * 用户登录业务逻辑：
     * 1.前端传入用户名和密码：需对用户名称和密码进行校验，不成功则返回相应的错误信息
     * 2.判断用户是否注册，根据用户名称去用户表查找数据是否存在
     * 3.校验密码，和数据库中的MD5的值进行比较
     * 4.从缓存中获取token信息，如果存在则更新token过期时间，不存在则设置token,利用UUID生成token，保存到缓存中，key为：USER：+用户的id，并设置过期日期为30分钟
     * @param users
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "用户登录的接口")
    @RequestMapping(path="/login")
    public ApiResponse login(@RequestBody Users users) throws Exception {
        if(StringUtils.isBlank(users.getUsername())){
            return ApiResponse.errorMsg("用户名称不能为空!");
        }
        if(StringUtils.isBlank(users.getPassword())){
            return ApiResponse.errorMsg("用户密码不能为空!");
        }
        boolean exists = userService.userExists(users.getUsername());
        if(!exists){
            return ApiResponse.errorMsg("请先注册，再登录!");
        }
        final Users user = userService.findByUserName(users.getUsername());
        if(user.getPassword().equalsIgnoreCase(MD5Util.encode(users.getPassword()))){
            return ApiResponse.errorMsg("密码输入错误!");
        }
        final boolean hasKey = RedisOperator.hasKey(USERSESSIONID + user.getId());
        if(hasKey){
            RedisOperator.expire(USERSESSIONID + user.getId(),30*60);
        }
        final String token = RedisOperator.get(USERSESSIONID + user.getId());
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        userVo.setToken(token);
        return ApiResponse.ok(userVo);
    }
}

