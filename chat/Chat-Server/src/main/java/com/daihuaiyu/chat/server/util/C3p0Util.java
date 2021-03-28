package com.daihuaiyu.chat.server.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * C3p0工具类
 *
 * @author :daihuaiyu
 * @Description: C3p0工具类：获取连接
 * @create 2021/3/28 9:12
 */
@Configuration
public class C3p0Util {

    @Value("${c3p0.driverClass}")
    private String driverClass;   //数据库驱动

    @Value("${c3p0.jdbcUrl}")
    private String jdbcUrl;   //JDBC地址

    @Value("${c3p0.userName}")
    private String userName; //数据库用户名

    @Value("${c3p0.password}")
    private String password; //数据库密码

    @Value("${c3p0.initialPoolSize}")
    private String initialPoolSize; //数据库初始化连接

    public Connection getConnection() throws PropertyVetoException, SQLException {
        // 1. 获取数据库连接池
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        // 2. 连接数据库
        cpds.setDriverClass(driverClass);
        cpds.setJdbcUrl(jdbcUrl);
        cpds.setUser(userName);
        cpds.setPassword(password);
        // 3. 设置数据库初始的连接数
        cpds.setInitialPoolSize(Integer.parseInt(initialPoolSize));
        // 4. 获取Conenction连接对象
        Connection conn1 = cpds.getConnection();
        return conn1;
    }

    public static void main(String[] args) throws PropertyVetoException, SQLException {
        C3p0Util c3p0Util = new C3p0Util();
        c3p0Util.getConnection();
    }
}
