package com.daihuaiyu.chat.client.netty;

import com.daihuaiyu.chat.client.frame.Login;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * Netty客户端代码
 *
 * @author :daihuaiyu
 * @Description: Netty客户端代码
 * @create 2021/3/27 20:52
 */
@Slf4j
public class NettyClient {

    //启动Netty客户端
    public void startNettyClient(String ipAddress,int port) throws InterruptedException {
        Channel channel = bootstrap.connect(ipAddress, port).sync().channel();
        log.info("客户端启动成功,地址："+ipAddress+"，端口："+port);
        //通过channel连接服务端
        Login login = new Login(channel);
        login.init();
        //关闭通信
        channel.closeFuture().sync();
    }

    /**
     * 单例静态类
     */
    public static class SingleNettyClient {

        static  final NettyClient instance = new NettyClient();
    }

    public static NettyClient getInstance(){
        NettyClient instance = SingleNettyClient.instance;
        return instance;
    }
    EventLoopGroup eventLoopGroup =null;
    Bootstrap bootstrap =null;
    ChannelFuture channelFuture =null;
    public NettyClient(){
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap().group(eventLoopGroup).channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                ChannelPipeline pipeline = nioSocketChannel.pipeline();
                pipeline.addLast(new StringDecoder());
                pipeline.addLast(new StringEncoder());
                pipeline.addLast(new NettyClientHandler());
            }
        });

    }

}
