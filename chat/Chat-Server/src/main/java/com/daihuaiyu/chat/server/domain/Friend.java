package com.daihuaiyu.chat.server.domain;

public class Friend {
    private String user;
    private String linker;
    private Integer uId;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLinker() {
        return linker;
    }

    public void setLinker(String linker) {
        this.linker = linker;
    }

    public Integer getUid() {
        return uId;
    }

    public void setUid(Integer uId) {
        this.uId = uId;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "user='" + user + '\'' +
                ", linker='" + linker + '\'' +
                ", uid=" + uId +
                '}';
    }
}
