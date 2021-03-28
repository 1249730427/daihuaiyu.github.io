package com.daihuaiyu.chat.client.service.impl;

import com.daihuaiyu.chat.client.constant.EnMsgType;
import com.daihuaiyu.chat.client.frame.Login;
import com.daihuaiyu.chat.client.service.ChatHandleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.SynchronousQueue;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/3/28 15:05
 */
@Slf4j
@Service
public class GetFriendHandleServiceImpl implements ChatHandleService {
    @Override
    public void delMessage(SynchronousQueue<Object> queue, ObjectNode jsonNodes) {
        //获取登录用户的好友
        int count = jsonNodes.get("count").asInt();
        Login.friend = new String[count];
        for ( int i = 0;i<count;i++){
            Login.friend[i] = jsonNodes.get("res"+i).asText();
            log.info(jsonNodes.get("res"+i)+"");
        }
    }

    @Override
    public String getMSG_TYPE() {
        return EnMsgType.EN_MSG_GET_FRIEND.toString();
    }
}
