package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.constant.EnMsgType;
import com.daihuaiyu.chat.server.dao.FriendDao;
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
 * 删除好友业务处理逻辑
 *
 * @author :daihuaiyu
 * @Description:删除好友业务处理逻辑
 * @create 2021/3/27 22:36
 */
@Service
public class DelFriendHandleServiceImpl implements ChatHandleService {

    @Autowired
    private InformationDao informationDao;

    @Autowired
    private FriendDao friendDao;

    @Override
    public String handleService(ObjectNode message, Channel channel) throws PropertyVetoException, SQLException {
        Integer friendId = message.get("friendId").asInt();
        int userId = message.get("id").asInt();
        String localName = message.get("localName").asText();

        //封装发回客户端的JSON
        ObjectNode objectNode = JsonUtil.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_ACK.toString());
        objectNode.put("srctype",EnMsgType.EN_MSG_DEL_FRIEND.toString());
        objectNode.put("code",200);

        //验证是否存在当前好友
        Information information = informationDao.getInformation(friendId);
        String friendName = information.getNickName();
        //查询自己是否有该好友
        boolean exist = friendDao.isExist(friendName,userId);
        if (exist){
            //存在当前好友进行删除操作
            friendDao.delFriend(userId,friendName);
            friendDao.delFriend(friendId,localName);
            objectNode.put("code",300);
        }
        return objectNode.toString();
    }

    @Override
    public String getMSG_TYPE() {
        return String.valueOf(EnMsgType.EN_MSG_DEL_FRIEND);
    }
}
