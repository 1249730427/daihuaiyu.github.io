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
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

/**
 * Netty服务端
 *
 * @author daihuaiyu
 * @create: 2021-03-16 16:41
 **/
@Slf4j
public class EchoServer {

    private final static boolean SSL=System.getProperty("ssl")!=null;
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
        /* 步骤:
         * 1.创建一个ServerBootstrap bootstrap实例用来配置启动服务器
         * 2.bootstrap.group指定NioEventLoopGroup来接收处理新连接
         * 3.bootstrap.channel指定通道类型
         * 4.bootstrap.option设置一些参数
         * 5.bootstrap.handler设置日志记录
         * 6.bootstrap.childHandler指定连接请求，后续调用的channelHandler,可以处理TCP，HTTP协议，自定义等
         * 7.bootstrap.bind设置绑定的端口
         * 8.bootstrap.sync阻塞直至启动服务
         */
        EventLoopGroup bossEventLoopGroup = null;
        EventLoopGroup workEventLoopGroup = null;
        try {
            bossEventLoopGroup = new NioEventLoopGroup();
            workEventLoopGroup = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap().group(bossEventLoopGroup,workEventLoopGroup)
                    .channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG,100).handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(//new NettyHttpInitializer(sslContext));  //添加HTTP请求处理Handler
                    new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline channelPipeline = socketChannel.pipeline();
                            if(sslContext!=null){
                                channelPipeline.addLast(sslContext.newHandler(socketChannel.alloc()));
                            }
                            /*IdleStateHandler(long readerIdleTime, long writerIdleTime, long allIdleTime, TimeUnit unit)
                             * 1.readerIdleTime:隔多久检查一下读事件是否发生，如果 channelRead() 方法超过 readerIdleTime 时间未被调用则会触发超时事件调用 userEventTrigger() 方法
                             * 2.隔多久检查一下写事件是否发生，writerIdleTime 写空闲超时时间设定，如果 write() 方法超过 writerIdleTime 时间未被调用则会触发超时事件调用 userEventTrigger() 方法
                             * 3.隔多久检查读写事件
                             * 4.时间单位
                             * 可以分别控制读，写，读写超时的时间，单位为秒，如果是0表示不检测，所以如果全是0，则相当于没添加这个 IdleStateHandler，连接是个普通的短连接
                             */
                            channelPipeline.addLast(new IdleStateHandler(15,0,0, TimeUnit.SECONDS));
                            channelPipeline.addLast(new MessageDecoder());
                            channelPipeline.addLast(new MessageEncoder());
                            channelPipeline.addLast(new LoggingHandler(LogLevel.INFO));
                            channelPipeline.addLast(new EchoServerHandler());
                            channelPipeline.addLast(new KpServerHandler());  //添加服务端心跳检测逻辑
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

