package mediator;

import java.sql.SQLException;
import java.util.List;

/**
 * 定义操作数据库的API
 *
 * @author daihuaiyu
 * @create: 2021-03-01 15:02
 **/
public interface SqlSession {

    <T> T selectOne(String statement);

    <T> T selectOne(String statement, Object parameter) throws SQLException, IllegalAccessException, ClassNotFoundException;

    <T> List<T> selectList(String statement) throws SQLException, ClassNotFoundException;

    <T> List<T> selectList(String statement, Object parameter) throws SQLException, IllegalAccessException, ClassNotFoundException;

    void close() throws SQLException;
}
