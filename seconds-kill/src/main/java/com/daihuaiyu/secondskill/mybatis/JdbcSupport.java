package com.daihuaiyu.secondskill.mybatis;

/**
 * 生成分页SQL和分页计数SQL接口
 * @Author: hydai
 * @Date: 2021/6/22 07:01
 * @Description:
 */
public interface JdbcSupport {

    String generatePageSql(Pager pager,String sql); //生成分页sql

    String generateCountSql(String sql);  //生成计数sql
}
