package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.service.ChatHandleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.nio.channels.Channel;

/**
 * 登陆逻辑业务处理逻辑
 *
 * @author :daihuaiyu
 * @Description:登陆逻辑业务处理逻辑
 * @create 2021/3/27 22:25
 */
@Service
public class LoginHandleServiceImpl implements ChatHandleService {
    @Override
    public String handleService(ObjectNode message, Channel channel) {
        return null;
    }
}
