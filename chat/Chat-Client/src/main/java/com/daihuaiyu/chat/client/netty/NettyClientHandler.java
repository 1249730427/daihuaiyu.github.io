package com.daihuaiyu.chat.client.netty;

import com.daihuaiyu.chat.client.constant.EnMsgType;
import com.daihuaiyu.chat.client.factory.MsgTypeFactory;
import com.daihuaiyu.chat.client.frame.ChatFrame;
import com.daihuaiyu.chat.client.service.ChatHandleService;
import com.daihuaiyu.chat.client.util.JsonUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.SynchronousQueue;

/**
 * Netty客户端处理逻辑
 *
 * @author :daihuaiyu
 * @Description: Netty客户端处理逻辑
 * @create 2021/3/28 13:52
 */
@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<String> {

    //定义一个同步阻塞队列
    public static SynchronousQueue<Object> queue = new SynchronousQueue<>();

    public static String Nickname;
    public String Signature;

    @Autowired
    private MsgTypeFactory msgTypeFactory;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        //DO NOTHING
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("收到服务端"+ctx.channel().remoteAddress()+"发送的消息"+msg);
        //解析服务端发送的消息
        ObjectNode jsonNodes = JsonUtil.getObjectNode((String) msg);
        String msgtype = jsonNodes.get("msgtype").asText();
        ChatHandleService chatHandleService;
        if(EnMsgType.EN_MSG_ACK.toString().equals(msgtype)){
            String srctype = jsonNodes.get("srctype").asText();
            chatHandleService = msgTypeFactory.getChatHandleService(srctype);
            chatHandleService.delMessage(queue,jsonNodes);
        }else if (EnMsgType.EN_MSG_VERIFY_PASSWORD.toString().equals(msgtype)){
            //修改密码
            int code = 0;
            code = jsonNodes.get("code").asInt();
            queue.offer(code);
        }else if (EnMsgType.EN_MSG_CHAT.toString().equals(msgtype)){
            //接收端接受消息  封装朋友昵称
            String message = " "+ jsonNodes.get("message").asText();
            //聊天显示框读取消息
            ChatFrame.sb.append(message+"\n");
            ChatFrame.displayTextPanel.setText(ChatFrame.sb.toString());
        }
    }
}
