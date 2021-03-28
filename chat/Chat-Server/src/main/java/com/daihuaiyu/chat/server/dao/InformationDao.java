package com.daihuaiyu.chat.server.dao;

import com.daihuaiyu.chat.server.domain.Information;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

public interface InformationDao {

    //获取用户登录信息
    Information getInformation(Integer id) throws PropertyVetoException, SQLException;

    //将修改后的签名存储到SQL
    void storeSignature(String signature, Integer id) throws PropertyVetoException, SQLException;

    //将修改后的昵称存储到SQL
    void storeNickname(String nickname, Integer id) throws PropertyVetoException, SQLException;

    //获取id
    Information nicknameGetId(String nickname) throws PropertyVetoException, SQLException;

}
