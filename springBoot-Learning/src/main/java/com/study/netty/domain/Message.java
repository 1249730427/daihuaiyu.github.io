package com.study.netty.domain;

import com.study.netty.encoder.MessageDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author daihuaiyu
 * @create: 2021-03-16 17:39
 **/
public class Message {
    private Header header;

    private String data;

    public Message(Header header, String data) {
        this.header = header;
        this.data = data;
    }

    public Message() {
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public byte[] toByte() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(MessageDecoder.PACKAGE_LENGTH);
        byteArrayOutputStream.write(header.getEncode());
        byteArrayOutputStream.write(header.getEncrypt());
        byteArrayOutputStream.write(header.getExtend1());
        byteArrayOutputStream.write(header.getExtend2());
        byte[] bytes = new byte[32];
        byte[] bytes1 = header.getSessionid().getBytes();
        for (int i = 0; i < bytes1.length; i++) {
            bytes[i] = bytes1[i];
        }
        byteArrayOutputStream.write(bytes);
        byte[] dataBytes = data.getBytes("UTF-8");
        byteArrayOutputStream.write(intToBytes2(dataBytes.length));
        byteArrayOutputStream.write(intToBytes2(header.getCammand()));
        byteArrayOutputStream.write(dataBytes);
        byteArrayOutputStream.write('\n');
        return byteArrayOutputStream.toByteArray();
    }

    private static byte[] intToBytes(int newint) {
        byte[] intbyte = new byte[4];
        intbyte[3] = (byte) ((newint >> 24) & 0xFF);
        intbyte[2] = (byte) ((newint >> 16) & 0xFF);
        intbyte[1] = (byte) ((newint >> 8) & 0xFF);
        intbyte[0] = (byte) (newint & 0xFF);
        return intbyte;

    }

    public static byte[] intToBytes2(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    private static int bytesToInt(byte [] srcByte ,int offset){
        int value;
        value = (int) ((srcByte[offset] & 0xFF) | ((srcByte[offset + 1] & 0xFF) << 8) | ((srcByte[offset + 2] & 0xFF) << 16) | ((srcByte[offset + 3] & 0xFF) << 24));
        return  value;
    }

    public static void main(String[] args) throws IOException {
        ByteBuf byteBuf = Unpooled.buffer();
        System.out.println(byteBuf);
        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        byteArrayOutputStream.write(intToBytes2(2));
        byte [] data=byteArrayOutputStream.toByteArray();
        byteBuf.writeBytes(data);
        System.out.println(byteBuf);
        int a = byteBuf.readInt();
        System.out.println(a);

    }
}

