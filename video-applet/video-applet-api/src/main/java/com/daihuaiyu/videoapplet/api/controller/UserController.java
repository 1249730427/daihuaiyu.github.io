package com.daihuaiyu.videoapplet.api.controller;

import com.daihuaiyu.videoapplet.api.service.UserService;
import com.daihuaiyu.videoapplet.api.util.ApiResponse;
import com.daihuaiyu.videoapplet.core.domain.UserVo;
import com.daihuaiyu.videoapplet.core.domain.Users;
import io.swagger.annotations.ApiOperation;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关信息Controller
 *
 * @author daihuaiyu
 * @create: 2021-03-31 16:32
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 获取用户信息业务逻辑
     * 1.客户端传入用户的ID，服务端根据用户ID查找用户信息
     * 2.转换为UserVo返回前端
     * @param id
     * @return
     */
    @ApiOperation(value = "用户信息查询", notes = "用户信息查询的接口")
    @PostMapping("/findUserInfo")
    public ApiResponse findUserInfo(@RequestParam("id")String id){
        if(StringUtils.isBlank(id)){
            return ApiResponse.errorMsg("用户的ID为空!");
        }
        UserVo userVo = new UserVo();
         Users users = userService.fingById(id);
        BeanUtils.copyProperties(users,userVo);
        return ApiResponse.ok(userVo);
    }
}

