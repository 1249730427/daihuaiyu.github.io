package mediator;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * SqlSession实现类
 *
 * @author daihuaiyu
 * @create: 2021-03-01 15:06
 **/
public class DefaultSqlSession implements SqlSession {

    private Connection connection;

    private Map<String, XNode> mapperElement;

    public DefaultSqlSession(Connection connection, Map<String, XNode> mapperElement) {
        this.connection = connection;
        this.mapperElement = mapperElement;
    }

    @SneakyThrows
    @Override
    public <T> T selectOne(String statement) {
        XNode xNode = mapperElement.get(statement);
        PreparedStatement preparedStatement = connection.prepareStatement(xNode.getSql());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> object = resultSet2Obj(resultSet, Class.forName(xNode.getResultType()));
        return object.get(0);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) throws SQLException, IllegalAccessException, ClassNotFoundException {
        XNode xNode = mapperElement.get(statement);
        Map<Integer, String> parameterMap = xNode.getParameter();
        PreparedStatement preparedStatement = connection.prepareStatement(xNode.getSql());
        buildParameter(preparedStatement,parameter,parameterMap);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> object = resultSet2Obj(resultSet, Class.forName(xNode.getResultType()));
        return object.get(0);
    }

    @Override
    public <T> List<T> selectList(String statement) throws SQLException, ClassNotFoundException {
        XNode xNode = mapperElement.get(statement);
        PreparedStatement preparedStatement = connection.prepareStatement(xNode.getSql());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> object = resultSet2Obj(resultSet, Class.forName(xNode.getResultType()));
        return object;
    }

    @Override
    public <T> List<T> selectList(String statement, Object parameter) throws SQLException, IllegalAccessException, ClassNotFoundException {
        XNode xNode = mapperElement.get(statement);
        Map<Integer, String> parameterMap = xNode.getParameter();
        PreparedStatement preparedStatement = connection.prepareStatement(xNode.getSql());
        buildParameter(preparedStatement,parameter,parameterMap);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> object = resultSet2Obj(resultSet, Class.forName(xNode.getResultType()));
        return object;
    }

    @Override
    public void close() throws SQLException {
        if(connection!=null){
            connection.close();
        }

    }
    private void buildParameter(PreparedStatement preparedStatement, Object parameter, Map<Integer, String> parameterMap) throws SQLException, IllegalAccessException {
        //单个参数
        if(parameter instanceof  Long){
            preparedStatement.setLong(1,(Long)parameter);
            return;
        }
        if(parameter instanceof  String){
            preparedStatement.setString(1,(String)parameter);
            return;
        }
        if(parameter instanceof Integer){
            preparedStatement.setLong(1,(Integer)parameter);
            return;
        }
        //参数是对象
        Field[] declaredFields = parameter.getClass().getDeclaredFields();
        Map<String,Object> filedMap = new HashMap<>();
        for(Field field:declaredFields){
            String fieldName = field.getName();
            field.setAccessible(true);
            Object o = field.get(parameter);
            field.setAccessible(false);
            filedMap.put(fieldName,o);
        }
        int size = parameterMap.size();
        //将对象的值利用Map转换成我们数据库的列，并设置值
        for(int i=0;i<size;i++){
            String parameterDefine=parameterMap.get(i);
            Object obj = filedMap.get(parameterDefine);
            if (obj instanceof Short) {
                preparedStatement.setShort(i, Short.parseShort(obj.toString()));
                continue;
            }

            if (obj instanceof Integer) {
                preparedStatement.setInt(i, Integer.parseInt(obj.toString()));
                continue;
            }

            if (obj instanceof Long) {
                preparedStatement.setLong(i, Long.parseLong(obj.toString()));
                continue;
            }

            if (obj instanceof String) {
                preparedStatement.setString(i, obj.toString());
                continue;
            }

            if (obj instanceof Date) {
                preparedStatement.setDate(i, (java.sql.Date) obj);
            }

        }
    }

    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> resultList = new ArrayList<>();

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                //获取传入Class的实例
                T object = (T) clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //遍历获取某一列的值
                    Object resultSetObject = resultSet.getObject(i);
                    //遍历获取某一列的列名
                    String columnName = metaData.getColumnName(i);
                    //利用java中反射给具体的实例设置值
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method method;
                    if (resultSetObject instanceof Timestamp) {
                        method = clazz.getMethod(setMethod, Date.class);
                    } else {
                        method = clazz.getMethod(setMethod, resultSetObject.getClass());
                    }
                    //反射
                    method.invoke(object, resultSetObject);
                }
                resultList.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}

