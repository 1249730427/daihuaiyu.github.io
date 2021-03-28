package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.constant.EnMsgType;
import com.daihuaiyu.chat.server.dao.FriendDao;
import com.daihuaiyu.chat.server.dao.InformationDao;
import com.daihuaiyu.chat.server.dao.UserDao;
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
 * 添加好友业务处理逻辑
 *
 * @author :daihuaiyu
 * @Description:添加好友业务处理逻辑
 * @create 2021/3/27 22:35
 */
@Service
public class AddFriendsHandleServiceImpl implements ChatHandleService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private InformationDao informationDao;

    @Autowired
    private FriendDao friendDao;

    @Override
    public String handleService(ObjectNode message, Channel channel) throws PropertyVetoException, SQLException {
        Integer friendId = message.get("friendId").asInt();
        int userId = message.get("id").asInt();
        String localName = message.get("localName").asText();

        //验证是否有ID
        boolean exists = userDao.verifyExistFriend(friendId);
        ObjectNode objectNode = JsonUtil.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_ACK.toString());
        objectNode.put("srctype",EnMsgType.EN_MSG_ADD_FRIEND.toString());
        objectNode.put("code",200);

        if(exists){
            //表示存在此id
            objectNode.put("code",300);
            //获取好友昵称
            Information information = informationDao.getInformation(friendId);
            String friendNickname = information.getNickName();
            //进行添加好友的操作   两个对应的信息都应该添加
            friendDao.addFriends(userId,localName,friendNickname);
            friendDao.addFriends(friendId,friendNickname,localName);
        }
        return objectNode.toString();
    }

    @Override
    public String getMSG_TYPE() {
        return String.valueOf(EnMsgType.EN_MSG_ADD_FRIEND);
    }
}
