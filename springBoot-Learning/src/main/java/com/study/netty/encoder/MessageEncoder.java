package com.study.netty.encoder;


import com.study.netty.domain.Header;
import com.study.netty.domain.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Message加密器
 *
 * @author daihuaiyu
 * @create: 2021-03-16 17:44
 **/
public class MessageEncoder extends MessageToByteEncoder<Message> {


    //从Message中获取数据，解析成字节后，写入到ByteBuff中
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        Header header =message.getHeader();
        byteBuf.writeByte(MessageDecoder.PACKAGE_LENGTH);
        byteBuf.writeByte(header.getEncode());
        byteBuf.writeByte(header.getEncrypt());
        byteBuf.writeByte(header.getExtend1());
        byteBuf.writeByte(header.getExtend2());
        byteBuf.writeBytes(header.getSessionid().getBytes());
        byteBuf.writeInt(header.getLength());
        byteBuf.writeInt(header.getCammand());
        byteBuf.writeBytes(message.getData().getBytes("UTF-8"));
    }
}

