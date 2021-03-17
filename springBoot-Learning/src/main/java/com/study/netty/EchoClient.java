package com.study.netty;

import com.study.netty.encoder.MessageDecoder;
import com.study.netty.encoder.MessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;


/**
 * Netty客户端
 *
 * @author :daihuaiyu
 * @create 2021/3/16 22:06
 */
@Slf4j
public class EchoClient {

    private static final boolean SSL=System.getProperty("ssl") !=null;

    private static final String IPADDRESS = System.getProperty("ipAddress","127.0.0.1");

    private static final int PORT = Integer.parseInt(System.getProperty("port","8097"));

//    private static final int SIZE = Integer.parseInt(System.getProperty("size","256"));

    public static void main(String[] args) throws SSLException {
        log.info("EchoClient.main");
        final SslContext sslContext;
        if(SSL){
            sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        }else{
            sslContext =null;
        }
        // Configure the client.
        /*创建一个Bootstrap bootstrap实例用来配置启动客户端
         * bootstrap.group指定NioEventLoopGroup来处理连接，接收数据
         * bootstrap.channel指定通道类型
         * bootstrap.option配置参数
         * bootstrap.handler客户端成功连接服务器后就会执行
         * bootstrap.connect客户端连接服务器
         * bootstrap.sync阻塞配置完成并启动
         */
        EventLoopGroup eventLoopGroup = null;
        try {
            eventLoopGroup = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap().group(eventLoopGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true).handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline channelPipeline = socketChannel.pipeline();
                            if(sslContext!=null){
                                channelPipeline.addLast(sslContext.newHandler(socketChannel.alloc(),IPADDRESS,PORT));
                            }
                            channelPipeline.addLast(new LoggingHandler(LogLevel.INFO));
                            channelPipeline.addLast(new MessageEncoder());
                            channelPipeline.addLast(new MessageDecoder());
                            channelPipeline.addLast(new EchoClientHandler());
                        }
                    });

            final ChannelFuture channelFuture = bootstrap.connect(IPADDRESS, PORT).sync();
            log.info("EchoClient.main ServerBootstrap配置启动完成");

            // Wait until the connection is closed.
            channelFuture.channel().closeFuture().sync();
            log.info("EchoClient.end");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //关闭eventLoopGroup
            eventLoopGroup.shutdownGracefully();
        }

    }

}
