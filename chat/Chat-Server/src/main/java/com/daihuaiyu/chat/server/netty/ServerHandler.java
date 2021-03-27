package com.daihuaiyu.chat.server.netty;

import com.daihuaiyu.chat.server.controller.NettyController;
import com.daihuaiyu.chat.server.util.CacheUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 服务处理业务逻辑类
 *
 * @author :daihuaiyu
 * @Description:
 * @create 2021/3/27 21:49
 */
@Slf4j
@Component
public class ServerHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        //DO NOTHING
    }

    //接收到客户端请求做某事
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("接收到channel地址："+ctx.channel().remoteAddress()+"的消息"+msg);
        Channel channel = ctx.channel();
        String message = NettyController.process((String)msg,channel);
        if(message !=null && message.length()>0){
            ctx.writeAndFlush(message); //写会客户端
        }
    }

    //用户上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info(ctx.channel().remoteAddress()+"上线了");
    }

    //用户下线
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        CacheUtil.del(channel); //删除用户缓存
        log.info(channel.remoteAddress()+"下线了");
    }
}
