package com.daihuaiyu.chat.client.service.impl;

import com.daihuaiyu.chat.client.constant.EnMsgType;
import com.daihuaiyu.chat.client.service.ChatHandleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.concurrent.SynchronousQueue;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/3/28 15:10
 */
@Service
public class ActiveStateHandleServiceImpl implements ChatHandleService {
    @Override
    public void delMessage(SynchronousQueue<Object> queue, ObjectNode jsonNodes) {
        //好友在线状态
        queue.offer(jsonNodes.get("code").asInt());
    }

    @Override
    public String getMSG_TYPE() {
        return EnMsgType.EN_MSG_ACTIVE_STATE.toString();
    }
}
