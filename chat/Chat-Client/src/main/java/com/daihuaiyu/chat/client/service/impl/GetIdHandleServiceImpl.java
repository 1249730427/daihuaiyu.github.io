package com.daihuaiyu.chat.client.service.impl;

import com.daihuaiyu.chat.client.constant.EnMsgType;
import com.daihuaiyu.chat.client.service.ChatHandleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.concurrent.SynchronousQueue;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/3/28 15:03
 */
@Service
public class GetIdHandleServiceImpl implements ChatHandleService {
    @Override
    public void delMessage(SynchronousQueue<Object> queue, ObjectNode jsonNodes) {
        int uid = jsonNodes.get("uid").asInt();
        queue.offer(uid);
    }

    @Override
    public String getMSG_TYPE() {
        return EnMsgType.EN_MSG_GET_ID.toString();
    }
}
