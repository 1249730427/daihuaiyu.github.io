package com.daihuaiyu.videoapplet.api.service;

import com.daihuaiyu.videoapplet.core.domain.UsersLikeVideos;

/**
 * 用户点赞取消点赞Service
 *
 * @author daihuaiyu
 * @create: 2021-04-15 14:32
 **/
public interface UserLikeVideoService {

    /**用户点赞接口*/
    void like(String id, String videoId, String videoCreateId);

    /**用户取消点赞接口*/
    void unlike(String id, String videoId, String videoCreateId);

    /**根据用户ID和视频ID找到用户点赞的信息*/
    UsersLikeVideos findByUserIdAndVideoId(String id, String videoId);

    /**查询用户是否点赞了视频信息*/
    boolean UserIsLike(String id, String videoId);
}
