package com.daihuaiyu.secondskill.exception;

import com.daihuaiyu.secondskill.config.CodeEnum;
import com.daihuaiyu.secondskill.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常捕获处理器
 *
 * @author daihuaiyu
 * @create: 2021-05-10 17:40
 **/
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletRequest httpServletRequest,Exception e){
        //自身抛的异常
        if(e instanceof GlobalException){
            GlobalException globalException = (GlobalException) e;
            return Result.error(globalException.getCm());
        }else if(e instanceof BindException){ //Bind异常
            BindException bindException = (BindException) e;
            List<ObjectError> allErrors = bindException.getAllErrors();
            ObjectError objectError = allErrors.get(0);
            String message = objectError.getDefaultMessage();
            logger.info("错误信息："+message);
            return Result.error(CodeEnum.BIND_ERROR);
        }else{
            return Result.error(CodeEnum.SERVER_ERROR);
        }
    }
}

