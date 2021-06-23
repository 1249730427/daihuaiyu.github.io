package com.daihuaiyu.secondskill.mybatis;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

/**
 * 分页拦截器，用于SQL拦截拦截，做分页处理
 *
 * @Author: hydai
 * @Date: 2021/6/22 07:22
 * @Description:
 */
@Intercepts(
        @Signature(type = StatementHandler.class, method = "query", args = {Connection.class})
)
public class PagerInterceptor implements Interceptor {

    private JdbcSupport jdbcSupport = new JdbcSupportImpl();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
       StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        Map<String,Object> parameterMap = (Map<String, Object>) statementHandler.getParameterHandler().getParameterObject();
        String sql = statementHandler.getBoundSql().getSql();
        for(String set :parameterMap.keySet()){
            if(parameterMap.get(set) instanceof  Pager){
                Pager pager = (Pager) parameterMap.get(set);
                //获取参数Connection
                Connection connection = (Connection) invocation.getArgs()[0];
                PreparedStatement preparedStatement = connection.prepareStatement(jdbcSupport.generateCountSql(sql));
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    pager.setTotalNum(resultSet.getInt("1"));
                }
                MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
                String pageSql = jdbcSupport.generatePageSql(pager,sql);
                metaObject.setValue("delegate.boundSql.sql", pageSql);
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        if(o instanceof StatementHandler){
            return Plugin.wrap(o,this);
        }else{
            return o;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
