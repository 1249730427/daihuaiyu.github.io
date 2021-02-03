package com.study.chapter1.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

/**
 * @author :daihuaiyu
 * @Description: 用户实体类
 * @create 2021/2/3 22:08
 */
@Data
@NoArgsConstructor
public class UserVo {

    @Id

    private Long id;

    private String userName;

    private Integer age;

    public UserVo(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }
}
