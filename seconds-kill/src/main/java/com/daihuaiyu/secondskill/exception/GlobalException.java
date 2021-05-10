package com.daihuaiyu.secondskill.exception;

import com.daihuaiyu.secondskill.config.CodeEnum;

/**
 * 全局异常
 *
 * @author daihuaiyu
 * @create: 2021-05-10 17:47
 **/
public class GlobalException extends RuntimeException {

    private CodeEnum  codeEnum;

    public GlobalException(CodeEnum cm) {
        super(cm.toString());
        this.codeEnum = cm;
    }

    public CodeEnum getCm() {
        return codeEnum;
    }
}

