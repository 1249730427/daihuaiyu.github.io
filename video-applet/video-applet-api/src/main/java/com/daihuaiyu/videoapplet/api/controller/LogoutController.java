package com.daihuaiyu.videoapplet.api.controller;

import com.daihuaiyu.videoapplet.api.util.ApiResponse;
import com.daihuaiyu.videoapplet.api.util.RedisOperatorUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注销Controller
 *
 * @author daihuaiyu
 * @create: 2021-03-31 10:20
 **/
@RestController
public class LogoutController {

    private final static String USERSESSIONID="user-session-id";

    @Autowired
    private RedisOperatorUtil RedisOperator;
    /**
     * 用户注销业务逻辑
     * 1.删除缓存中的token信息
     * @param id
     * @return
     */
    @ApiOperation(value = "用户注销", notes = "用户注销的接口")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "String" ,paramType = "query")
    @RequestMapping(path="/login")
    public ApiResponse logout(@RequestParam("id")String id){
        if(RedisOperator.hasKey(USERSESSIONID+id)){
            RedisOperator.del(USERSESSIONID+id);
        }
        return ApiResponse.build(200,"OK",null);
    }
}

