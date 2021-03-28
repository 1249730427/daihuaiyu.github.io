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
 * 获取登陆信息业务逻辑
 *
 * @author :daihuaiyu
 * @Description:获取登陆信息业务逻辑
 * @create 2021/3/27 22:29
 */
@Service
public class GetInformationHandleServiceImpl implements ChatHandleService {

    private static InformationDao informationDao;

    static {
        informationDao = SpringUtil.getBean(InformationDao.class);
    }

    @Override
    public String handleService(ObjectNode message, Channel channel) throws PropertyVetoException, SQLException {
        int id = message.get("id").asInt();

        Information information = informationDao.getInformation(id);

        //封装JSON发回客户端
        ObjectNode objectNode = JsonUtil.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_ACK.toString());
        objectNode.put("srctype",EnMsgType.EN_MSG_GETINFORMATION.toString());
        objectNode.put("Nickname",information==null?"":information.getNickName());
        objectNode.put("Signature",information==null?"":information.getSignature());

        return objectNode.toString();
    }

    @Override
    public String getMSG_TYPE() {
        return String.valueOf(EnMsgType.EN_MSG_GETINFORMATION);
    }
}
