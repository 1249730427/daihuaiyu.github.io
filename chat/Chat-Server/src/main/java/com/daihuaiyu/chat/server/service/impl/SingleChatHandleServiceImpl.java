package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.constant.EnMsgType;
import com.daihuaiyu.chat.server.service.ChatHandleService;
import com.daihuaiyu.chat.server.util.CacheUtil;
import com.daihuaiyu.chat.server.util.JsonUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;


/**
 * 单聊模式业务处理逻辑
 *
 * @author :daihuaiyu
 * @Description:单聊模式业务处理逻辑
 * @create 2021/3/27 22:32
 */
@Service
public class SingleChatHandleServiceImpl implements ChatHandleService {

    @Override
    public String handleService(ObjectNode message, Channel channel) {
        int id = message.get("id").asInt();

        //根据id在friend表获取登录用户名

        //封装JSON数据服务端转发数据
        ObjectNode objectNode = JsonUtil.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_ACK.toString());
        objectNode.put("srctype",EnMsgType.EN_MSG_CHAT.toString());

        //客户端保证用户独立存在且是好友
        Channel channel1 = CacheUtil.get(id);
        //判断用户是否在线
        if (channel1 == null){
            //用户不在线
            objectNode.put("code",200);
        }else{
            //用户在线
            objectNode.put("code",300);
            //消息转发
            channel.writeAndFlush(message.toString());
        }
        return objectNode.toString();
    }

    @Override
    public String getMSG_TYPE() {
        return String.valueOf(EnMsgType.EN_MSG_CHAT);
    }
}
