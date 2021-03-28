package com.daihuaiyu.chat.client.service.impl;

import com.daihuaiyu.chat.client.constant.EnMsgType;
import com.daihuaiyu.chat.client.service.ChatHandleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.concurrent.SynchronousQueue;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/3/28 15:02
 */
@Service
public class SingleChatHandleServiceImpl implements ChatHandleService {
    @Override
    public void delMessage(SynchronousQueue<Object> queue, ObjectNode jsonNodes) {
        //发送端返回消息
        queue.offer(jsonNodes.get("code").asInt());
    }

    @Override
    public String getMSG_TYPE() {
        return EnMsgType.EN_MSG_CHAT.toString();
    }
}
