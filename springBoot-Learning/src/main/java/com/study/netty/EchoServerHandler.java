package com.study.netty;

import com.study.netty.domain.Header;
import com.study.netty.domain.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端业务逻辑处理类
 *
 * @author :daihuaiyu
 * @Description:
 * @create 2021/3/16 21:42
 */
@Slf4j
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    //接收请求后的处理类
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        log.info("接受到的数据data:"+message.getData());

        //此处编写接收到客户端请求后的业务逻辑
        String content="hello world,this is nettyServer";
        Header header=new Header((byte)0, (byte)1, (byte)1, (byte)1, (byte)0, "713f17ca614361fb257dc6741332caf2",content.getBytes("UTF-8").length, 1);
        Message message1 = new Message(header, content);
        ctx.writeAndFlush(message1);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("EchoServerHandler.channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("错误信息"+cause.getCause());
        cause.printStackTrace();
        ctx.close();
    }
}
