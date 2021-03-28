package com.daihuaiyu.chat.server.controller;

import com.daihuaiyu.chat.server.factory.MsgTypeFactory;
import com.daihuaiyu.chat.server.service.ChatHandleService;
import com.daihuaiyu.chat.server.util.JsonUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 * 服务端业务处理逻辑
 *
 * @author :daihuaiyu
 * @Description:服务端业务处理逻辑：接收客户端消息后处理
 * @create 2021/3/27 22:05
 */
@Component
public class NettyController {

    @Autowired
    private MsgTypeFactory msgTypeFactory;

    public String process(String msg, Channel channel) throws PropertyVetoException, SQLException {

        ObjectNode jsonNodes = JsonUtil.getObjectNode(msg);  //将字符串装换为JSON,并获取对应的
        String msgType = jsonNodes.get("msgtype").asText(); //获取msgtype
        ChatHandleService chatHandleService = msgTypeFactory.getChatHandleService(msgType); //获取对应的处理类
        //发送的消息类型不在定义的范围内
        if(chatHandleService ==null){
            return "";
        }
        return chatHandleService.handleService(jsonNodes, channel);
    }

}
