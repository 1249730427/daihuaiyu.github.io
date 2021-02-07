package com.study.chapter1.entity;

import lombok.Data;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/2/7 20:57
 */
@Data
public class UserInfo {

    /** 用户名 */
    private String username;

    /** 手机号 */
    private String mobile;

    /** 邮箱 */
    private String email;

    /** 密码 */
    private String password;

    /** 盐 */
    private String salt;

    private AccessToken accessToken;

    public UserInfo(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }
}
