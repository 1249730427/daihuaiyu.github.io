package com.study.netty;

import com.study.netty.encoder.MessageDecoder;
import com.study.netty.encoder.MessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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

    public static void main(String[] args) throws CertificateException, SSLException {

        log.info("EchoServer.main start");
        final SslContext sslContext ;
        if(SSL){
            SelfSignedCertificate selfSignedCertificate = new SelfSignedCertificate();
            sslContext = SslContextBuilder.forServer(selfSignedCertificate.certificate(),selfSignedCertificate.privateKey()).build();
        }else{
            sslContext =null;
        }
        EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workEventLoopGroup = new NioEventLoopGroup();
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
                    }
                });


    }
}

