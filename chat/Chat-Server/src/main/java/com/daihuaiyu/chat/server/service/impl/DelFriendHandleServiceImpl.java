package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.service.ChatHandleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.nio.channels.Channel;

/**
 * 删除好友业务处理逻辑
 *
 * @author :daihuaiyu
 * @Description:删除好友业务处理逻辑
 * @create 2021/3/27 22:36
 */
@Service
public class DelFriendHandleServiceImpl implements ChatHandleService {
    @Override
    public String handleService(ObjectNode message, Channel channel) {
        return null;
    }
}
