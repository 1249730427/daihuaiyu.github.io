package com.study.chapter1.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * API结果
 *
 * @author daihuaiyu
 * @create: 2021-02-07 13:59
 **/
@Data
@Entity
@NoArgsConstructor
public class ApiResult {

    private String code;

    private String message;

    public ApiResult(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

