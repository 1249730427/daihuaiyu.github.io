package com.daihuaiyu.chat.server.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.Channel;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 * 业务处理类接口
 *
 * @author :daihuaiyu
 * @Description: 业务处理类接口：对不同的业务做了接口适配
 * @create 2021/3/27 22:16
 */
public interface ChatHandleService {

     String handleService(ObjectNode jsonNodes, Channel channel) throws PropertyVetoException, SQLException;

     String getMSG_TYPE();
}
