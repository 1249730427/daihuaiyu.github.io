package mediator;

import com.alibaba.fastjson.JSON;
import domain.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SqlSessionHandler
 * @Author: hydai
 * @Date: 2021/6/15 05:51
 * @Description:
 */
public class SqlSessionHandler implements InvocationHandler {

    private Connection connection;

    private Map<String, XNode> mapperElement;

    public SqlSessionHandler(Connection connection, Map<String, XNode> mapperElement) {
        this.connection = connection;
        this.mapperElement = mapperElement;
    }

    public SqlSessionHandler() {
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession defaultSqlSession = new DefaultSqlSession(connection, mapperElement);
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(mapperElement.get("dao.IUserDao1.queryUserInfoById").getSql());
            preparedStatement.setLong(1,  args==null?1L:(Long)args[0]);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                userList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return userList.get(0).toString();
    }
}
