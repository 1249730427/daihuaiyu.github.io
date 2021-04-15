package com.daihuaiyu.videoapplet.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户点赞取消点赞实体类
 *
 * @author daihuaiyu
 * @create: 2021-04-15 14:25
 **/

@Entity
@Table(name = "users_like_videos")
public class UsersLikeVideos {
    @Id
    private String id;

    /**用户*/
    @Column(name = "user_id")
    private String userId;

    /**视频*/
    @Column(name = "video_id")
    private String videoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}