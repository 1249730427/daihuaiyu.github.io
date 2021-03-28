package com.daihuaiyu.chat.server.constant;

/**
 * 消息枚举定义
 *
 * @author :daihuaiyu
 * @Description: 消息枚举定义类：定义各种消息常量
 * @create 2021/3/27 22:53
 */
public enum EnMsgType {

    EN_MSG_LOGIN,             //用户登陆消息
    EN_MSG_MODIFY_SIGNATURE, // 修改用户签名
    EN_MSG_MODIFY_NICKNAME, //修改用户昵称
    EN_MSG_GETINFORMATION, //获取登录用户的信息
    EN_MSG_VERIFY_PASSWORD, //用户修改 密码
    EN_MSG_CHAT,            //单聊模式
    EN_MSG_GET_ID,          //获取用户ID
    EN_MSG_GET_FRIEND,      //获取用户好友列表
    EN_MSG_ADD_FRIEND,      //添加好友
    EN_MSG_DEL_FRIEND,      //删除好友
    EN_MSG_ACTIVE_STATE,   //判断好友在线状态
    EN_MSG_ACK            //响应消息

}
