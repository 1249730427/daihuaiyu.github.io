package com.daihuaiyu.chat.client.service.impl;

import com.daihuaiyu.chat.client.constant.EnMsgType;
import com.daihuaiyu.chat.client.frame.Linkmen;
import com.daihuaiyu.chat.client.netty.NettyClientHandler;
import com.daihuaiyu.chat.client.service.ChatHandleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.concurrent.SynchronousQueue;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/3/28 15:01
 */
@Service
public class GetInformationHandleServiceImpl extends NettyClientHandler implements ChatHandleService {

    @Override
    public void delMessage(SynchronousQueue<Object> queue, ObjectNode jsonNodes) {
        //存取信息
        Nickname = jsonNodes.get("Nickname").asText();
        Signature = jsonNodes.get("Signature").asText();
        Linkmen.label_1.setText(Nickname);
        Linkmen.field.setText(Signature);
    }

    @Override
    public String getMSG_TYPE() {
        return EnMsgType.EN_MSG_GETINFORMATION.toString();
    }
}
