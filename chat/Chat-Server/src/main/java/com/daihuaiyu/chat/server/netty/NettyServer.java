package com.daihuaiyu.chat.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Value;

import java.net.Socket;

/**
 * Netty 服务器
 * @author :daihuaiyu
 * @Description: Netty 服务器
 * @create 2021/3/27 21:16
 */
public class NettyServer {

        @Value("${netty.server.port:8090}")
        private int Port;

        private EventLoopGroup workEventLoopGroup ;
        private EventLoopGroup bossEventLoopGroup ;
        private  ServerBootstrap bootstrap ;
        private ChannelFuture channelFuture;

        public  NettyServer(){
            workEventLoopGroup = new NioEventLoopGroup();  //处理请求Group
            bossEventLoopGroup = new NioEventLoopGroup();  //处理连接Group
            bootstrap = new ServerBootstrap().group(workEventLoopGroup,bossEventLoopGroup).option(ChannelOption.SO_BACKLOG,100).
                    //指定父循环组通道类型
                    handler(new LoggingHandler(LogLevel.DEBUG)).channel(NioServerSocketChannel.class)
                    ////指定子循环组Handler信息
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //获取容器  添加channelHandler
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //添加channelHandler
                            pipeline.addLast(new StringDecoder());  //字符串解密
                            pipeline.addLast(new StringEncoder());  //字符串加密
                            pipeline.addLast(new ServerHandler());
                        }
                    })
            ;
        }


}
