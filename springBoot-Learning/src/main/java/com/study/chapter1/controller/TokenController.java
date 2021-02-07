package com.study.chapter1.controller;

import com.study.chapter1.entity.AccessToken;
import com.study.chapter1.util.ApiCodeEnum;
import com.study.chapter1.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

/**
 * token获取controller
 *
 * @author daihuaiyu
 * @create: 2021-02-07 13:45
 **/
@Slf4j
@Controller
@RequestMapping(path = "/api/token")
public class TokenController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(path = "/api_toke")
    public ApiResponse<AccessToken> apiToken(String appId, @RequestHeader("timestamp") String timestamp, @RequestHeader("sign") String sign){
        if(StringUtils.isEmpty(appId) || StringUtils.isEmpty(timestamp)|| StringUtils.isEmpty(sign)){
            return ApiResponse.error(ApiCodeEnum.PARAMETER_ERROR.getMsg(),ApiCodeEnum.PARAMETER_ERROR.getMsg());
        }

        log.info("");
        return null;
    }



}

