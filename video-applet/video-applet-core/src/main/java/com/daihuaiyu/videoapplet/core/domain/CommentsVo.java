package com.daihuaiyu.videoapplet.core.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 评论回复包装类
 *
 * @author daihuaiyu
 * @create: 2021-04-15 17:27
 **/

@Entity
@Data
public class CommentsVo {
    @Id
    private String id;

    @Column(name = "father_comment_id")
    private String fatherCommentId;

    @Column(name = "to_user_id")
    private String toUserId;

    /** 视频id*/
    @Column(name = "video_id")
    private String videoId;

    /** 留言者，评论的用户id*/
    @Column(name = "from_user_id")
    private String fromUserId;

    /**评论内容*/
    private String comment;

    /**评论距现在的时间*/
    private String timeAgo;

    /**昵称*/
    private String nickname;

    /**头像*/
    private String faceImage;

    /**被回复对象的昵称*/
    private String toNickname;
}

