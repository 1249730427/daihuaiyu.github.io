package com.daihuaiyu.videoapplet.api.controller;

import com.daihuaiyu.videoapplet.api.service.UserService;
import com.daihuaiyu.videoapplet.api.util.ApiResponse;
import com.daihuaiyu.videoapplet.api.util.MD5Util;
import com.daihuaiyu.videoapplet.api.util.RedisOperatorUtil;
import com.daihuaiyu.videoapplet.core.domain.UserVo;
import com.daihuaiyu.videoapplet.core.domain.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 用户注册Controller
 *
 * @author daihuaiyu
 * @create: 2021-03-30 16:01
 **/
@RestController
@Api(value = "用户注册登录的接口",tags = {"注册和登录的controller"})
public class RegisterLoginController {

    private final static String USERSESSIONID="user-session-id";

    @Autowired
    private UserService userService;

    @Autowired
    private RedisOperatorUtil redisOperator;
    /**
     * 用户注册业务逻辑：
     * 1.前端传入用户的用户名和密码，需对用户名称和密码进行校验，不成功则返回相应的错误信息
     * 2.判断用户是否注册，根据用户名称去用户表查找数据是否存在
     * 3.若不存在，则开始注册,保存注册信息进数据库
     * 4.利用UUID生成token，保存到缓存中，key为：USER：+用户的id，并设置过期日期为30分钟
     * @param users
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "用户注册的接口")
    @RequestMapping(path="/regist")
    public ApiResponse userRegister(@RequestBody Users users) throws Exception {
        if(StringUtils.isBlank(users.getUsername())){
            return ApiResponse.errorMsg("用户名称不能为空!");
        }
        if(StringUtils.isBlank(users.getPassword())){
            return ApiResponse.errorMsg("用户密码不能为空!");
        }
        boolean exists = userService.userExists(users.getUsername());
        if(exists){
            return ApiResponse.errorMsg("该用户名已注册!");
        }
        Users user = new Users();
        user.setUsername(users.getUsername());
        user.setPassword(MD5Util.encode(users.getPassword()));
        user.setNickname(users.getUsername());
        user.setFaceImage("");
        user.setFansCounts(0);
        user.setFollowCounts(0);
        user.setReceiveLikeCounts(0);
        final Users users1 = userService.save(user);
        String token = UUID.randomUUID().toString();
        user.setPassword("");
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        userVo.setToken(token);
        redisOperator.set(USERSESSIONID+users1.getId(),token,30*60);
        return ApiResponse.ok(userVo);
    }
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
        final boolean hasKey = redisOperator.hasKey(USERSESSIONID + user.getId());
        if(hasKey){
            redisOperator.expire(USERSESSIONID + user.getId(),30*60);
        }
        final String token = redisOperator.get(USERSESSIONID + user.getId());
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        userVo.setToken(token);
        return ApiResponse.ok(userVo);
    }

    /**
     * 用户注销业务逻辑
     * 1.删除缓存中的token信息
     * @param id
     * @return
     */
    @ApiOperation(value = "用户注销", notes = "用户注销的接口")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "String" ,paramType = "query")
    @RequestMapping(path="/logout")
    public ApiResponse logout(@RequestParam("id")String id){
        if(redisOperator.hasKey(USERSESSIONID+id)){
            redisOperator.del(USERSESSIONID+id);
        }
        return ApiResponse.build(200,"OK",null);
    }
}

