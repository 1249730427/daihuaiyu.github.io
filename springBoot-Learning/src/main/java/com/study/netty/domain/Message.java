package com.study.netty.domain;

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
}

