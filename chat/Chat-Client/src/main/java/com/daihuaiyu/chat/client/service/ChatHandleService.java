package com.daihuaiyu.chat.client.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.concurrent.SynchronousQueue;

/**
 * 消息处理接口
 *
 * @author :daihuaiyu
 * @Description: 消息处理接口
 * @create 2021/3/28 14:32
 */
public interface ChatHandleService {

    void delMessage(SynchronousQueue<Object> queue, ObjectNode jsonNodes);

    String getMSG_TYPE();
}
