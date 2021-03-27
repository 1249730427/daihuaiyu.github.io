package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.service.ChatHandleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.nio.channels.Channel;

/**
 * 判断好友在线状态
 *
 * @author :daihuaiyu
 * @Description:判断好友在线状态
 * @create 2021/3/27 22:37
 */
@Service
public class FriendIsActiveHandleServiceImpl implements ChatHandleService {
    @Override
    public String handleService(ObjectNode message, Channel channel) {
        return null;
    }
}
