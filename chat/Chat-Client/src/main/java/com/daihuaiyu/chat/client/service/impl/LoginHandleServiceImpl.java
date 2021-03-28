package com.daihuaiyu.chat.client.service.impl;

import com.daihuaiyu.chat.client.constant.EnMsgType;
import com.daihuaiyu.chat.client.service.ChatHandleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.concurrent.SynchronousQueue;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/3/28 14:54
 */
@Service
public class LoginHandleServiceImpl implements ChatHandleService {

    @Override
    public void delMessage(SynchronousQueue<Object> queue, ObjectNode jsonNodes) {
        //登录操作
        queue.offer(jsonNodes.get("code").asInt());
    }

    @Override
    public String getMSG_TYPE() {
        return EnMsgType.EN_MSG_LOGIN.toString();
    }
}
