package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.constant.EnMsgType;
import com.daihuaiyu.chat.server.service.ChatHandleService;
import com.daihuaiyu.chat.server.util.CacheUtil;
import com.daihuaiyu.chat.server.util.JsonUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;


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
        int friendId = message.get("friendId").asInt();

        ObjectNode objectNode = JsonUtil.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_ACK.toString());
        objectNode.put("srctype",EnMsgType.EN_MSG_ACTIVE_STATE.toString());

        //客户端保证用户独立存在且是好友
        Channel channel1 = CacheUtil.get(friendId);
        //判断用户是否在线
        if (channel1 == null){
            //用户不在线
            objectNode.put("code",200);
        }else {
            //用户在线
            objectNode.put("code",300);
        }
        return objectNode.toString();
    }

    @Override
    public String getMSG_TYPE() {
        return String.valueOf(EnMsgType.EN_MSG_ACTIVE_STATE);
    }
}
