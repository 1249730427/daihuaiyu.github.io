package com.daihuaiyu.chat.server.dao;


import java.beans.PropertyVetoException;
import java.sql.SQLException;

public interface UserDao {

    boolean getInformation(int id, String passwd) throws PropertyVetoException, SQLException;

    //验证密码
    boolean verifyPassword(String oldPasswd, Integer id) throws PropertyVetoException, SQLException;

    //修改密码
    void modifyPasswd(String newPasswd, Integer id) throws PropertyVetoException, SQLException;

    //验证是否存在所添加好友的id
    boolean verifyExistFriend(Integer id) throws PropertyVetoException, SQLException;
}
