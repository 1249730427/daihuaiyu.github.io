package com.study.netty;

import com.study.netty.encoder.MessageDecoder;
import com.study.netty.encoder.MessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * Netty服务端
 *
 * @author daihuaiyu
 * @create: 2021-03-16 16:41
 **/
@Slf4j
public class EchoServer {

    private final static boolean SSL=System.getProperty("ssl")==null;
    private final static Integer PORT = Integer.parseInt(System.getProperty("port","8097"));

    public static void main(String[] args) throws CertificateException, SSLException, InterruptedException {

        log.info("EchoServer.main start");
        final SslContext sslContext ;
        if(SSL){
            SelfSignedCertificate selfSignedCertificate = new SelfSignedCertificate();
            sslContext = SslContextBuilder.forServer(selfSignedCertificate.certificate(),selfSignedCertificate.privateKey()).build();
        }else{
            sslContext =null;
        }
        // Configure the server.
        /*步骤
         * 创建一个ServerBootstrap b实例用来配置启动服务器
         * b.group指定NioEventLoopGroup来接收处理新连接
         * b.channel指定通道类型
         * b.option设置一些参数
         * b.handler设置日志记录
         * b.childHandler指定连接请求，后续调用的channelHandler
         * b.bind设置绑定的端口
         * b.sync阻塞直至启动服务
         */
        EventLoopGroup bossEventLoopGroup = null;
        EventLoopGroup workEventLoopGroup = null;
        try {
            bossEventLoopGroup = new NioEventLoopGroup();
            workEventLoopGroup = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap().group(bossEventLoopGroup,workEventLoopGroup)
                    .channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG,100)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline channelPipeline = socketChannel.pipeline();
                            if(sslContext!=null){
                                channelPipeline.addLast(sslContext.newHandler(socketChannel.alloc()));
                            }
                            channelPipeline.addLast(new MessageDecoder());
                            channelPipeline.addLast(new MessageEncoder());
                            channelPipeline.addLast(new LoggingHandler(LogLevel.INFO));
                            channelPipeline.addLast(new EchoServerHandler());
                        }
                    });
            //启动Server
            ChannelFuture channelFuture = bootstrap.bind(PORT).sync();
            log.info("EchoServer.main ServerBootstrap配置启动完成");

            //等待直到服务Socket关闭
            channelFuture.channel().closeFuture().sync();
            log.info("EchoServer.main end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            // Shut down all event loops to terminate all threads.
            bossEventLoopGroup.shutdownGracefully();
            workEventLoopGroup.shutdownGracefully();
        }

    }
}

