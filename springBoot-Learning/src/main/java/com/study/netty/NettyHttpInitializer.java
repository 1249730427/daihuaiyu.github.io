package com.study.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;


/**
 * Http协议处理
 *
 * @author :daihuaiyu
 * @Description:
 * @create 2021/3/18 20:43
 */
public class NettyHttpInitializer extends ChannelInitializer {

    private final SslContext sslContext;

    public NettyHttpInitializer(final SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline channelPipeline = channel.pipeline();
        if(sslContext!=null){
            channelPipeline.addLast(sslContext.newHandler(channel.alloc()));
        }
        channelPipeline.addLast(new HttpServerCodec());
        channelPipeline.addLast(new NettyHttpServerHandler());
    }
}
