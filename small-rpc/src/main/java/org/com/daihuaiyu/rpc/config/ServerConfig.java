package org.com.daihuaiyu.rpc.config;

/**
 * 注册中心配置
 * @Author: daihuaiyu
 * @Date: 2021/7/26 17:14
 * @Description:
 */
public class ServerConfig {

    protected String host;

    protected int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
