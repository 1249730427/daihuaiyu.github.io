package com.study.netty;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端心跳检测逻辑
 *
 * @author :daihuaiyu
 * @Description:
 * @create 2021/3/20 9:55
 */
@Slf4j
public class KpServerHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("server channelActive");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        String message = (String) msg;
        if("heartbeat".equalsIgnoreCase(message)){
            log.info(channelHandlerContext.channel().remoteAddress()+ "===>server: " + message);
            channelHandlerContext.writeAndFlush("heartbeat");
        }
    }

    /**
     * 如果5s没有读取请求，则向客户端发送心跳
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){  //如果是心跳event
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if(IdleState.READER_IDLE.equals(idleStateEvent.state()) ){
                ctx.writeAndFlush("heartbeat").addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
