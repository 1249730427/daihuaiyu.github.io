package com.study.chapter2.config;

/**
 * 路由枚举值
 *
 * @Enum: DataSourceEnum
 * @Author: hydai
 * @Date: 2021/5/5 08:19
 * @Description:
 */
public enum DataSourceEnum {

    MASTER("master"),
    SLAVER("slaver");

    private String sourceName;

    DataSourceEnum(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
