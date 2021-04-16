package com.daihuaiyu.videoapplet.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户关注取消关注实体类
 *
 * @author daihuaiyu
 * @create: 2021-04-16 14:09
 **/
@Entity
@Table(name = "users_fans")
public class UsersFans {
    @Id
    private String id;

    /**用户*/
    @Column(name = "user_id")
    private String userId;

    /*** 粉丝*/
    @Column(name = "fan_id")
    private String fanId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFanId() {
        return fanId;
    }

    public void setFanId(String fanId) {
        this.fanId = fanId;
    }
}

