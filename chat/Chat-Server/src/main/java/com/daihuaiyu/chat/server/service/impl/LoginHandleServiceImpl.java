package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.constant.EnMsgType;
import com.daihuaiyu.chat.server.dao.UserDao;
import com.daihuaiyu.chat.server.service.ChatHandleService;
import com.daihuaiyu.chat.server.util.CacheUtil;
import com.daihuaiyu.chat.server.util.JsonUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyVetoException;
import java.sql.SQLException;


/**
 * 登陆逻辑业务处理逻辑
 *
 * @author :daihuaiyu
 * @Description:登陆逻辑业务处理逻辑
 * @create 2021/3/27 22:25
 */
@Service
public class LoginHandleServiceImpl implements ChatHandleService {

    @Autowired
    private UserDao userDao;

    @Override
    public String handleService(ObjectNode message, Channel channel) throws PropertyVetoException, SQLException {
        int id = message.get("id").asInt(); //获取ID
        String passwd = message.get("passwd").asText();//获取用户密码
        boolean existInformation = userDao.getInformation(id, passwd); //数据库查询用户信息存不存在
        ObjectNode jsonNodes = JsonUtil.getObjectNode();
        //定义响应信息
        jsonNodes.put("msgtype",EnMsgType.EN_MSG_ACK.toString());
        jsonNodes.put("srctype",EnMsgType.EN_MSG_LOGIN.toString());
        jsonNodes.put("code",300);
        if(existInformation){
            //响应码
            jsonNodes.put("code",200);
            CacheUtil.put(id,channel);
        }
        return jsonNodes.toString();
    }

    @Override
    public String getMSG_TYPE() {
        return String.valueOf(EnMsgType.EN_MSG_LOGIN);
    }
}
