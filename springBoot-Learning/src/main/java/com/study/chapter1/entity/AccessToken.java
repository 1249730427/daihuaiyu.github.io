package com.study.chapter1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

/**
 * token实体类
 *
 * @author daihuaiyu
 * @create: 2021-02-07 14:02
 **/
@Entity
@Data
@NoArgsConstructor
public class AccessToken {

    private String token;

    private Date expireTime;

    public AccessToken(String token, Date expireTime) {
        this.token = token;
        this.expireTime = expireTime;
    }
}

