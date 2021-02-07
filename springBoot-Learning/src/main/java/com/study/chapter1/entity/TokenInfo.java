package com.study.chapter1.entity;

import lombok.Data;

/**
 * @author :daihuaiyu
 * @Description: TokenInfo
 * @create 2021/2/7 20:56
 */
@Data
public class TokenInfo {

    private Integer type;

    private AppInfo appInfo;

    private UserInfo userInfo;


}
