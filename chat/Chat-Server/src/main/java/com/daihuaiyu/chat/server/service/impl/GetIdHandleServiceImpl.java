package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.service.ChatHandleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.nio.channels.Channel;

/**
 * 获取ID业务处理逻辑
 *
 * @author :daihuaiyu
 * @Description:获取ID业务处理逻辑
 * @create 2021/3/27 22:34
 */
@Service
public class GetIdHandleServiceImpl implements ChatHandleService {
    @Override
    public String handleService(ObjectNode message, Channel channel) {
        return null;
    }
}
