package com.daihuaiyu.secondskill.controller;

import com.daihuaiyu.secondskill.rabbitmq.MessageSender;
import com.daihuaiyu.secondskill.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 模拟发送MQ信息
 *
 * @author daihuaiyu
 * @create: 2021-05-26 15:39
 **/
@Controller
public class SampleController {

    @Autowired
    private MessageSender sender;

    @RequestMapping("/mq/header")
    @ResponseBody
    public Result<String> header() {
		sender.sendHeader("Hello，world");
        return Result.success("Hello，world");
    }

	@RequestMapping("/mq/fanout")
    @ResponseBody
    public Result<String> fanout() {
		sender.sendFanout("Hello，world");
        return Result.success("Hello，world");
    }

	@RequestMapping("/mq/topic")
    @ResponseBody
    public Result<String> topic() {
		sender.sendTopic("Hello，world");
        return Result.success("Hello，world");
    }

	@RequestMapping("/mq")
    @ResponseBody
    public Result<String> mq() {
		sender.send("Hello，world");
        return Result.success("Hello，world");
    }
}

