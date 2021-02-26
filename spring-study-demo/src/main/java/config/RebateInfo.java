package config;

import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * MQ统一消息体
 *
 * @author daihuaiyu
 * @create: 2021-02-25 17:15
 **/
public class RebateInfo {

    private String userId;

    private String bizId;

    private Date bizTime;

    private String bizDesc;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public Date getBizTime() {
        return bizTime;
    }

    public void setBizTime(String bizTime) {
        this.bizTime = new Date(Long.parseLong("1591077840669"));
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

}

