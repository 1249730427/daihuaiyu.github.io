package com.daihuaiyu.videoapplet.api.controller;

import com.daihuaiyu.videoapplet.api.util.ApiResponse;
import com.daihuaiyu.videoapplet.core.domain.Users;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册Controller
 *
 * @author daihuaiyu
 * @create: 2021-03-30 16:01
 **/
@RestController
public class RegisterController {

    public ApiResponse userRegister(@RequestBody Users users){
        //TO DO
        return null;
    }
}

