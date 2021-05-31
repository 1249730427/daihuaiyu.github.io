package com.daihuaiyu.secondskill.config;


/**
 * @Enum: CodeEnum
 * @Author: hydai
 * @Date: 2021/5/9 22:33
 * @Description:
 */
public enum CodeEnum {
    SUCCESS(0, "success"),
    SERVER_ERROR(500100, "服务端异常"),
    BIND_ERROR(500101, "参数校验异常"),
    MIAOSHA_FAIL(500102,"秒杀失败"),
    VERIFYCODE_INVALID(500103,"验证码输入错误"),
    MIAOSHA_PATH_INVALID(500104,"秒杀路径有误"),
    ACCESS_LIMIT_REACHED(500105,"访问太频繁"),
    //登录模块 5002XX
    SESSION_ERROR (500210, "Session不存在或者已经失效"),
    PASSWORD_EMPTY (500211, "登录密码不能为空"),
    MOBILE_EMPTY(500212, "手机号不能为空"),
    MOBILE_ERROR(500213, "手机号格式错误"),
    MOBILE_NOT_EXIST(500214, "手机号不存在"),
    PASSWORD_ERROR (500215, "密码错误"),
    //商品模块 5003XX

    //订单模块 5004XX
    ORDER_NOT_EXIST (500400, "订单不存在"),
    //秒杀模块 5005XX
  	MIAO_SHA_OVER(500500, "商品已经秒杀完毕"),
    REPEATE_MIAOSHA(500501, "不能重复秒杀");
    private Integer code;

    private String message;
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
