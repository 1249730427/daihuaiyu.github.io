package com.study.netty;

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

    private static final String ipAddress = System.getProperty("ipAddress","127.0.0.1");

    private static final int PORT = Integer.parseInt(System.getProperty("port","8097"));

    private static final int size = Integer.parseInt(System.getProperty("size","256"));

    public static void main(String[] args) throws CertificateException, SSLException {
        log.info("EchoClient.main");
        final SslContext sslContext;
        if(SSL){
            SelfSignedCertificate selfSignedCertificate = new SelfSignedCertificate();
            sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        }else{
            sslContext =null;
        }
    }

}
