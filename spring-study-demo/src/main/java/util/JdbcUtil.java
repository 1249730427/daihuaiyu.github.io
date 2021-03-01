package util;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * JDBC工具类
 *
 * @author daihuaiyu
 * @create: 2021-03-01 14:18
 **/
@Slf4j
public class JdbcUtil {

    private final  static String URL ="jdbc:mysql://127.0.0.1:3306/user?characterEncoding=utf8&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC&socketTimeout=9000&serverTimezone=Asia/Shanghai";

    private final static String USERNAME = "root";

    private final static String PASSWORD ="123456";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载类驱动器
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获取数据库连接
        Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        //3.操作数据库
        ResultSet resultSet = connection.createStatement().executeQuery("select * from user ");
        while(resultSet.next()){
            log.info("用户姓名：name {}" ,resultSet.getString("username"));
        }
    }
}

