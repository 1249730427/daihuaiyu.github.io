package com.daihuaiyu.videoapplet.api.util;

import lombok.Data;

/**
 * 方法结果效应
 *
 * @author daihuaiyu
 * @create: 2021-03-30 14:15
 * @description:自定义响应数据结构
 *	这个类是提供给门户，ios，安卓，微信商城用的
 * 	门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 	其他自行处理
 * 	200：表示成功
 *	500：表示错误，错误信息在msg字段中
 * 	501：bean验证错误，不管多少个错误都以map形式返回
 * 	502：拦截器拦截到用户token出错
 *	555：异常抛出信息
 **/
@Data
public class ApiResponse {

    private Integer status; //响应状态码

    private String msg; //响应内容

    private Object data; //响应数据

    /**返回状态码以及响应数据*/
    public static ApiResponse build(Integer status, String msg, Object data){
        return new ApiResponse(status,msg,data);
    }

    /**成功状态响应数据,状态码为200,响应数据*/
    public static  ApiResponse ok(Object data){
        return new ApiResponse(200,"OK",data);
    }

    /**失败时响应，状态码为500，响应数据为null*/
    public static ApiResponse error(String msg){
        return new ApiResponse(500,msg,null);
    }

    /**失败响应，状态码为501，响应数据为null*/
    public static ApiResponse errorMap(Object data) {
        return new ApiResponse(501, "error", data);
    }

    /**失败响应，状态码为502，响应数据为null*/
    public static ApiResponse errorTokenMsg(String msg) {
        return new ApiResponse(502, msg, null);
    }

    /**失败响应，状态码为503，响应数据为null*/
    public static ApiResponse errorException(String msg) {
        return new ApiResponse(555, msg, null);
    }
    public ApiResponse(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiResponse() {
    }

    public ApiResponse(Object data) {
        this.data = data;
    }
}

