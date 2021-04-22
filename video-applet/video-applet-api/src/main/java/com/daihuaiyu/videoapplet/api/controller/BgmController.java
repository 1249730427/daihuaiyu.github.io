package com.daihuaiyu.videoapplet.api.controller;

import com.daihuaiyu.videoapplet.api.service.BgmService;
import com.daihuaiyu.videoapplet.api.util.ApiResponse;
import com.daihuaiyu.videoapplet.core.domain.Bgm;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 背景音乐处理器
 *
 * @author daihuaiyu
 * @create: 2021-04-19 17:57
 **/
@RestController
public class BgmController {


        @Autowired
        private BgmService bgmService;


        @ApiOperation(value = "查询BGM列表", notes = "查询bgm列表的接口")
        @PostMapping("/bgmList")
        public ApiResponse bgmList(){
            //查询全部都bgm列表
            List<Bgm> allBgm = bgmService.findAllBgm();
            return ApiResponse.ok(allBgm);
        }
}

