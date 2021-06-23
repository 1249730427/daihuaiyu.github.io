package com.daihuaiyu.secondskill.mybatis;

/**
 * 分页对象信息
 * @Author: hydai
 * @Date: 2021/6/22 06:50
 * @Description:
 */
public class Pager {

    private int pageNo; //当前页数

    private int pageSize = 20; //每页的数据量

    private int totalNum ; //数据总数

    private String sortBy; //排序字段

    private String rank = "DESC"; // 排序规则，默认降序

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
