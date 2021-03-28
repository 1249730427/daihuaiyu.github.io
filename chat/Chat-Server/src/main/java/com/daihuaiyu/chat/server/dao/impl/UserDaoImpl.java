package com.daihuaiyu.chat.server.dao.impl;

import com.daihuaiyu.chat.server.dao.UserDao;
import com.daihuaiyu.chat.server.util.C3p0Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private C3p0Util c3p0Util;

    @Override
    public boolean getInformation(int id, String passwd) throws PropertyVetoException, SQLException {
        //建立连接
        Connection connection = c3p0Util.getConnection();
        String sql = "select * from user where user_id = ? and passwd = ?";

        //查询成功的标志
        boolean flag = false;

        try {
            //查询数据库
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,passwd);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                //表示查询成功
                flag = true;
            }

            //关闭资源
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //验证密码
    @Override
    public boolean verifyPassword(String oldPasswd, Integer id) throws PropertyVetoException, SQLException {
        boolean flag = false;

        //获取连接
        Connection connection = c3p0Util.getConnection();
        String sql = "select *from user where passwd = ? and user_id = ?";

        //查询数据库
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,oldPasswd);
            preparedStatement.setInt(2,id);

            //执行preparedStatement里面的SQL
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                //密码验证正确
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //验证密码
    @Override
    public void modifyPasswd(String newPasswd, Integer id) throws PropertyVetoException, SQLException {

        //建立连接
        Connection connection = c3p0Util.getConnection();
        String sql = "update user set passwd = ? where user_id = ?";

        //查询数据库
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,newPasswd);
            preparedStatement.setInt(2,id);

            //执行sql
            preparedStatement.executeUpdate();

            //关闭资源
            if (preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //验证是否存在好友
    @Override
    public boolean verifyExistFriend(Integer id) throws PropertyVetoException, SQLException {
        Connection connection = c3p0Util.getConnection();
        String sql = "select *from user where user_id = ?";
        boolean flag = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
