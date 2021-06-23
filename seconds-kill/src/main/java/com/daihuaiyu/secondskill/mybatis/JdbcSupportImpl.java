package com.daihuaiyu.secondskill.mybatis;

/**
 * 生成分页SQL和分页计数SQL具体实现
 * @Author: hydai
 * @Date: 2021/6/22 07:04
 * @Description:
 */
public class JdbcSupportImpl implements JdbcSupport {

    @Override
    public String generatePageSql(Pager pager, String sql) {
        int startIndex = (pager.getPageNo()-1)*pager.getPageSize()+1; //起始位置
        int endIndex = startIndex+pager.getPageSize()-1;  //结束位置
        StringBuffer sqlBuffer = new StringBuffer(sql);
        sqlBuffer.append("order by "+pager.getSortBy() ==null?"id":pager.getSortBy());
        sqlBuffer.append(pager.getRank()==null?"DESC":pager.getRank());
        sqlBuffer.append("LIMIT "+startIndex+" ,"+endIndex);
        return sqlBuffer.toString();
    }

    @Override
    public String generateCountSql(String sql) {
        String countSql = "SELECT COUNT(1) FROM( "+sql+") A ";
        return countSql;
    }
}
