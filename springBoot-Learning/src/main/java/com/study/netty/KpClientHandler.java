package com.study.netty;

import com.study.netty.domain.Header;
import com.study.netty.domain.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端心跳检测逻辑
 *
 * @author :daihuaiyu
 * @Description:
 * @create 2021/3/20 10:11
 */
@Slf4j
public class KpClientHandler extends SimpleChannelInboundHandler {

    /**客户端心跳命令*/
    private static final ByteBuf HEARTBEAT_SEQUENCE =
            Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("heartbeat", CharsetUtil.UTF_8));

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client channelActive");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        String message = (String) msg;
        if("heartbeat".equalsIgnoreCase(message)){
            log.info(channelHandlerContext.channel().remoteAddress()+ "===>client: " + message);
//            channelHandlerContext.writeAndFlush("heartbeat");
        }
    }

    /**
     * 如果4s没有收到写请求，则向服务端发送心跳请求
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if(IdleState.WRITER_IDLE.equals(idleStateEvent.state())){
                String content = "heartbeat";
                Header header = new Header((byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 0, "713f17ca614361fb257dc6741332caf2", content.getBytes("UTF-8").length, 1);
                Message message = new Message(header, content);
                ctx.writeAndFlush(message).addListener(ChannelFutureListener.CLOSE_ON_FAILURE) ;
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
