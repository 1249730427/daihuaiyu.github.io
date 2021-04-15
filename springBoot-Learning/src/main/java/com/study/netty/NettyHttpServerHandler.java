package com.study.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpHeaders.*;
import static io.netty.util.CharsetUtil.*;

/**
 * Http请求处理器
 *
 * @author :daihuaiyu
 * @Description:
 * @create 2021/3/18 20:51
 */
public class NettyHttpServerHandler extends ChannelInboundHandlerAdapter {

    public final byte[] CONNTEXT = "你好，世界！powered by netty Server".getBytes();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        if(msg instanceof HttpRequest){
            HttpRequest httpRequest = (HttpRequest) msg;
            //100 Continue含义
            //HTTP客户端程序有一个实体的主体部分要发送给服务器，但希望在发送之前查看下服务器是否会接受这个实体，所以在发送实体之前先发送了一个携带100 Continue的Expect请求首部的请求。
            //服务器在收到这样的请求后，应该用 100 Continue或一条错误码来进行响应。
            if(is100ContinueExpected(httpRequest)){
                ctx.write( new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE));
            }
            /**HTTP请求响应
             *
             * boolean keepAlive = isKeepAlive(httpRequest);
             * FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK, Unpooled.wrappedBuffer(CONNTEXT));
             * fullHttpResponse.headers().set(CONTENT_TYPE,"text/plain;charset=UTF-8");
             * fullHttpResponse.headers().setInt(CONTENT_LENGTH,fullHttpResponse.content().readableBytes());
             * if(!keepAlive){
                ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                }
             * fullHttpResponse.headers().set(CONNECTION,KEEP_ALIVE);
             * ctx.write(fullHttpResponse);
             **/

            // 获取请求的uri
            String uri = httpRequest.uri();
            Map<String,String> resMap = new HashMap<>();
            resMap.put("method",httpRequest.method().name());
            resMap.put("uri",uri);
            String msg1 = "<html><head><title>test</title></head><body>你请求uri为：" + uri+"</body></html>";
            // 创建http响应
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.copiedBuffer(msg1, UTF_8));
            // 设置头信息
            response.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
            //response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
            // 将html write到客户端
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
