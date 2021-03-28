package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.config.SpringUtil;
import com.daihuaiyu.chat.server.constant.EnMsgType;
import com.daihuaiyu.chat.server.dao.InformationDao;
import com.daihuaiyu.chat.server.domain.Information;
import com.daihuaiyu.chat.server.service.ChatHandleService;
import com.daihuaiyu.chat.server.util.JsonUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyVetoException;
import java.sql.SQLException;


/**
 * 获取ID业务处理逻辑
 *
 * @author :daihuaiyu
 * @Description:获取ID业务处理逻辑
 * @create 2021/3/27 22:34
 */
@Service
public class GetIdHandleServiceImpl implements ChatHandleService {

    private static InformationDao informationDao;

    static {
        informationDao = SpringUtil.getBean(InformationDao.class);
    }

    @Override
    public String handleService(ObjectNode message, Channel channel) throws PropertyVetoException, SQLException {
        String nickname = message.get("nickname").asText();
        Information information = informationDao.nicknameGetId(nickname);
        //联系人的id
        int uid = information.getuId();
        //封装JSON
        ObjectNode objectNode = JsonUtil.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_ACK.toString());
        objectNode.put("srctype",EnMsgType.EN_MSG_GET_ID.toString());
        objectNode.put("uid",uid);
        return objectNode.toString();
    }

    @Override
    public String getMSG_TYPE() {
        return String.valueOf(EnMsgType.EN_MSG_GET_ID);
    }
}
