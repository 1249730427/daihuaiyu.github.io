package com.study.chapter1.entity;

import lombok.Data;

/**
 * @author :daihuaiyu
 * @Description: AppInfo
 * @create 2021/2/7 20:41
 */
@Data
public class AppInfo {

    private String appId;

    private String securityKey;

    public AppInfo(String appId, String securityKey) {
        this.appId = appId;
        this.securityKey = securityKey;
    }

}
