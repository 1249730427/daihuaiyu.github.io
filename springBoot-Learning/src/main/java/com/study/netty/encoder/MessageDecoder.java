package com.study.netty.encoder;

import com.study.netty.domain.Header;
import com.study.netty.domain.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

/**
 * Message解析器
 *
 * @author daihuaiyu
 * @create: 2021-03-16 17:13
 **/
public class MessageDecoder extends ByteToMessageDecoder {

    /**
     * 包长度标志头
     */
    private final static int HEAD_LENGTH = 45;

    /**
     * 标志头
     */
    public final static byte PACKAGE_LENGTH = 0x01;

    //从ByteBuf中获取字节，转换成对象，写入到List中
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byteBuf.markReaderIndex();
        if (byteBuf.readableBytes() < HEAD_LENGTH) {
            throw new CorruptedFrameException("包长度问题");
        }
        byte readByte = byteBuf.readByte();
        if (readByte != PACKAGE_LENGTH) {
            throw new CorruptedFrameException("标志错误");
        }
        final byte encode = byteBuf.readByte();
        final byte encrypt = byteBuf.readByte();
        final byte extend1 = byteBuf.readByte();
        final byte extend2 = byteBuf.readByte();
        byte sessionByte[] = new byte[32];
        byteBuf.readBytes(sessionByte);
        String sessionId = new String(sessionByte, "UTF-8");
        int length = byteBuf.readInt();
        int command = byteBuf.readInt();
        final Header header = new Header(readByte, encode, encrypt, extend1, extend2, sessionId, length, command);
        byte[] data = new byte[length];
        byteBuf.readBytes(data);
        final Message messgage = new Message(header, new String(data, "UTF-8"));
        list.add(messgage);
    }

}

