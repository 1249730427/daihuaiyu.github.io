package com.daihuaiyu.chat.server.service.impl;

import com.daihuaiyu.chat.server.constant.EnMsgType;
import com.daihuaiyu.chat.server.dao.FriendDao;
import com.daihuaiyu.chat.server.dao.UserDao;
import com.daihuaiyu.chat.server.service.ChatHandleService;
import com.daihuaiyu.chat.server.util.JsonUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


/**
 * 获取好友列表
 *
 * @author :daihuaiyu
 * @Description:获取好友列表
 * @create 2021/3/27 22:34
 */
@Service
public class GetFriendHandleServiceImpl implements ChatHandleService {

    @Autowired
    private FriendDao friendDao;

    @Override
    public String handleService(ObjectNode message, Channel channel) throws PropertyVetoException, SQLException {
        int uid = message.get("uid").asInt();

        //返回ArrayLis集合
        List<String> friends = friendDao.getFriends(uid);
        //封装JSON
        ObjectNode objectNode = JsonUtil.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_ACK.toString());
        objectNode.put("srctype",EnMsgType.EN_MSG_GET_FRIEND.toString());

        //写回friend集合
        Iterator<String> iterator = friends.iterator();
        int i = 0;
        while (iterator.hasNext()){
            objectNode.put("res"+i,iterator.next());
            i++;
        }
        //记录好友个数
        objectNode.put("count",i);

        return objectNode.toString();
    }

    @Override
    public String getMSG_TYPE() {
        return String.valueOf(EnMsgType.EN_MSG_GET_FRIEND);
    }
}
