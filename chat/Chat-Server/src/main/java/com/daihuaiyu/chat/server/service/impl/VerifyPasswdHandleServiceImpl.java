package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.constant.EnMsgType;
import com.daihuaiyu.chat.server.dao.UserDao;
import com.daihuaiyu.chat.server.service.ChatHandleService;
import com.daihuaiyu.chat.server.util.JsonUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyVetoException;
import java.sql.SQLException;


/**
 * 修改密码业务处理逻辑
 *
 * @author :daihuaiyu
 * @Description:修改密码业务处理逻辑
 * @create 2021/3/27 22:30
 */
@Service
public class VerifyPasswdHandleServiceImpl implements ChatHandleService {

    @Autowired
    private UserDao userDao;

    @Override
    public String handleService(ObjectNode message, Channel channel) throws PropertyVetoException, SQLException {
        int id = message.get("id").asInt();
        String oldPasswd = message.get("oldPasswd").asText();
        String newPasswd = message.get("newPasswd").asText();

        boolean exits = userDao.verifyPassword(oldPasswd, id);

        ObjectNode objectNode = JsonUtil.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_VERIFY_PASSWORD.toString());
        objectNode.put("code",200);
        if (exits){
            //验证成功
            userDao.modifyPasswd(newPasswd,id);
            objectNode.put("code",300);
        }
        return objectNode.toString();
    }

    @Override
    public String getMSG_TYPE() {
        return String.valueOf(EnMsgType.EN_MSG_VERIFY_PASSWORD);
    }
}
