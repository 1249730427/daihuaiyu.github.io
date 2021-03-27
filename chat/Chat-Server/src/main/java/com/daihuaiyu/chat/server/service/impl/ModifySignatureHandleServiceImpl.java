package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.service.ChatHandleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.nio.channels.Channel;

/**
 * 修改签名业务处理逻辑
 *
 * @author :daihuaiyu
 * @Description:修改签名业务处理逻辑
 * @create 2021/3/27 22:26
 */
@Service
public class ModifySignatureHandleServiceImpl implements ChatHandleService {

    @Override
    public String handleService(ObjectNode message, Channel channel) {
        return null;
    }
}
