package com.daihuaiyu.chat.server.domain;

public class Information {

    private int iId;
    private String nickName;
    private String signature;
    private int uId;

    public int getiId() {
        return this.iId;
    }

    public void setiId(final int iId) {
        this.iId = iId;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(final String signature) {
        this.signature = signature;
    }

    public int getuId() {
        return this.uId;
    }

    public void setuId(final int uId) {
        this.uId = uId;
    }

    @Override
    public String toString() {
        return "Information{" +
                "Iid=" + iId +
                ", nickname='" + nickName + '\'' +
                ", signature='" + signature + '\'' +
                ", uid=" + uId +
                '}';
    }
}
