package com.daihuaiyu.secondskill.config;

import lombok.Data;

/**
 * @Enum: CodeEnum
 * @Author: hydai
 * @Date: 2021/5/9 22:33
 * @Description:
 */
public enum CodeEnum {
    SUCCESS(0, "success"),
    SERVER_ERROR(500100, "服务端异常");
;
    private Integer code;

    private String message;
    //登录模块 5002XX

    //商品模块 5003XX

    //订单模块 5004XX

    //秒杀模块 5005XX
    CodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
