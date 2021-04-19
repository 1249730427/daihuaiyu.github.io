package com.daihuaiyu.videoapplet.api.service;

/**
 * 用户关注取消关注Service
 *
 * @author daihuaiyu
 * @create: 2021-04-19 15:11
 **/
public interface UserFansService {

    //用户关注他人
    void follow(String id,String fansId);

    //用户取消关注他人
    void unfollow(String id, String fansId);

    //查询用户是否被关注
    Boolean findIsFollowed(String id, String fansId);
}
