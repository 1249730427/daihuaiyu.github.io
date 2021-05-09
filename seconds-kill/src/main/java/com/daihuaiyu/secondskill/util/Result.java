package com.daihuaiyu.secondskill.util;

import com.daihuaiyu.secondskill.config.CodeEnum;
import lombok.Data;

/**
 * Restful架构风格响应类
 * @ClassName: Result
 * @Author: hydai
 * @Date: 2021/5/9 22:22
 * @Description:
 */
@Data
public class  Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public Result(T data) {
        this.code=0;
        this.msg="success";
        this.data=data;
    }

    public static <T> Result<T> success(T data){
        return new Result(data);
    }

    public static <T> Result<T> error(CodeEnum codeEnum){
        return new Result(codeEnum);
    }
    private Result(CodeEnum cm) {
        if(cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMessage();
    }

}
