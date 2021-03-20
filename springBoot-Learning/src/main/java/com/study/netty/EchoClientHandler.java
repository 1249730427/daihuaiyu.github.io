package com.study.netty;

import com.study.netty.domain.Header;
import com.study.netty.domain.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.UUID;

/**
 * EchoClient业务处理逻辑
 *
 * @author daihuaiyu
 * @create: 2021-03-17 10:11
 **/
@Slf4j
public class EchoClientHandler extends ChannelInboundHandlerAdapter {



    //客户端连接服务器后调用，简单的就ctx.writeAndFlush(ByteBuf)，复杂点就自定义编解码器
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接激活/channelActive");
        //ctx.writeAndFlush(Unpooled.copiedBuffer("hello world",CharsetUtil.UTF_8));
            String content = "hello world,this is netty client" + UUID.randomUUID().toString().replaceAll("-", "");
//            Scanner scanner = new Scanner(System.in);
//            String content = scanner.nextLine();
            Header header = new Header((byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 0, "713f17ca614361fb257dc6741332caf2", content.getBytes("UTF-8").length, 1);
            Message message = new Message(header, content);
            ctx.writeAndFlush(message);
    }

    //接收到数据后调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        Message msg1=(Message)msg;

        log.info("收到服务端消息："+msg1.getData());
    }

    //完成时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        log.info("消息发送完成：channelReadComplete");
        ctx.flush();
    }

    //发生异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}

