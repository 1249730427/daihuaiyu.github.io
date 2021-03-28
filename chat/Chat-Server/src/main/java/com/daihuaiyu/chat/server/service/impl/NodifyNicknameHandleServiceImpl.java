package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.constant.EnMsgType;
import com.daihuaiyu.chat.server.dao.InformationDao;
import com.daihuaiyu.chat.server.service.ChatHandleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyVetoException;
import java.sql.SQLException;


/**
 * 修改昵称业务处理逻辑
 *
 * @author :daihuaiyu
 * @Description:修改昵称业务处理逻辑
 * @create 2021/3/27 22:27
 */
@Service
public class NodifyNicknameHandleServiceImpl implements ChatHandleService {

    @Autowired
    private InformationDao informationDao;
    @Override
    public String handleService(ObjectNode message, Channel channel) throws PropertyVetoException, SQLException {
        int id = message.get("id").asInt();
        String nickname = message.get("nickname").asText();
        //进行存储
        informationDao.storeNickname(nickname,id);
        return "";
    }

    @Override
    public String getMSG_TYPE() {
        return String.valueOf(EnMsgType.EN_MSG_MODIFY_NICKNAME);
    }
}
