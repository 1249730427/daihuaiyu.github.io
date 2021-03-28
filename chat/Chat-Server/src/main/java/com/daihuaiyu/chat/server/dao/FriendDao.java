package com.daihuaiyu.chat.server.dao;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface FriendDao {

    //获取好友列表
    ArrayList<String> getFriends(Integer uid) throws PropertyVetoException, SQLException;

    //添加好友
    void addFriends(Integer userId, String localName, String friendNickname) throws PropertyVetoException, SQLException;

    //判断是否存在好友
    boolean isExist(String nickname, Integer friendId) throws PropertyVetoException, SQLException;

    //删除好友
    void delFriend(int userId, String friend) throws PropertyVetoException, SQLException;
}
