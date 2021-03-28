package com.daihuaiyu.chat.server.dao.impl;


import com.daihuaiyu.chat.server.dao.InformationDao;
import com.daihuaiyu.chat.server.domain.Information;
import com.daihuaiyu.chat.server.util.C3p0Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class InformationDaoImpl implements InformationDao {

    @Autowired
    private C3p0Util c3p0Util;

    @Override
    public Information getInformation(Integer id) throws PropertyVetoException, SQLException {

        Information information = null;

        //建立连接
        Connection connection = c3p0Util.getConnection();
        String sql = "select * from information where uid = ?";

        //查询数据库
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                information = new Information();
                information.setNickName(resultSet.getString("nickname"));
                information.setSignature(resultSet.getString("signature"));
            }
            //关闭资源
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return information;
    }

    //存储个性签名
    @Override
    public void storeSignature(String signature,Integer id) throws PropertyVetoException, SQLException {

        //建立连接
        Connection connection = c3p0Util.getConnection();
        String sql = "update information set signature = ? where uid = ?";

        try {
            //查询数据库
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,signature);
            preparedStatement.setInt(2,id);

            //执行preparedStatement里的SQL语句
            preparedStatement.executeUpdate();

            //关闭资源
            if (preparedStatement != null){
                preparedStatement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //存储昵称
    @Override
    public void storeNickname(String nickname, Integer id) throws PropertyVetoException, SQLException {

        //建立连接
        Connection connection = c3p0Util.getConnection();
        String sql = "update information set nickname = ? where uid = ?";

        try {
            //查询数据库
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,nickname);
            preparedStatement.setInt(2,id);

            //执行preparedStatement里的SQL语句
            preparedStatement.executeUpdate();

            //关闭资源
            if (preparedStatement != null){
                preparedStatement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //根据昵称获取id
    @Override
    public Information nicknameGetId(String nickname) throws PropertyVetoException, SQLException {

        Information information = null;

        //建立连接
        Connection connection = c3p0Util.getConnection();
        String sql = "select * from information where nickname = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,nickname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                information = new Information();
                information.setuId(resultSet.getInt("uid"));
            }

            //关闭资源
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return information;
    }



}
