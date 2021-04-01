package com.daihuaiyu.videoapplet.core.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Date;

/**
 * 视频实体类
 *
 * @author :daihuaiyu
 * @Description: 视频实体类
 * @create 2021/4/1 22:48
 */
@Entity
@ApiModel(value = "视频信息")
public class Video {

    @Id
    @ApiModelProperty(hidden = true)
    private String id;

    /**
     * 发布者ID
     */
    @ApiModelProperty(value = "发布者id", name = "user_id", example = "123", required = true)
    private String userId;

    /**
     * 用户使用音频的信息
     */
    @ApiModelProperty(value = "用户使用音频的信息", name = "audioId", example = "123", required = true)
    private String audioId;

    /**
     * 视频描述
     */
    @ApiModelProperty(value = "视频描述", name = "videoDesc", example = "123", required = true)
    private String videoDesc;

    /**
     * 视频存放的路径
     */
    @ApiModelProperty(value = "视频存放的路径", name = "videoPath", example = "123", required = true)
    private String videoPath;

    /**
     * 视频秒数
     */
    @ApiModelProperty(value = "视频秒数", name = "videoSeconds", example = "123.09", required = true)
    private Float videoSeconds;

    /**
     * 视频宽度
     */
    @ApiModelProperty(value = "视频宽度", name = "videoWidth")
    private Integer videoWidth;

    /**
     * 视频高度
     */
    @ApiModelProperty(value = "视频高度", name = "videoHeight")
    private Integer videoHeight;

    /**
     * 视频封面图
     */
    @ApiModelProperty(value = "视频封面图", name = "coverPath")
    private String coverPath;

    /**
     * 喜欢/赞美的数量
     */
    @ApiModelProperty(value = "喜欢/赞美的数量", name = "likeCounts")
    private Long likeCounts;

    /**
     * 视频状态：1、发布成功 2、禁止播放，管理员操作
     */
    @ApiModelProperty(value = "视频状态：1、发布成功 2、禁止播放，管理员操作", name = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;


    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getAudioId() {
        return this.audioId;
    }

    public void setAudioId(final String audioId) {
        this.audioId = audioId;
    }

    public String getVideoDesc() {
        return this.videoDesc;
    }

    public void setVideoDesc(final String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoPath() {
        return this.videoPath;
    }

    public void setVideoPath(final String videoPath) {
        this.videoPath = videoPath;
    }

    public Float getVideoSeconds() {
        return this.videoSeconds;
    }

    public void setVideoSeconds(final Float videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    public Integer getVideoWidth() {
        return this.videoWidth;
    }

    public void setVideoWidth(final Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    public Integer getVideoHeight() {
        return this.videoHeight;
    }

    public void setVideoHeight(final Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    public String getCoverPath() {
        return this.coverPath;
    }

    public void setCoverPath(final String coverPath) {
        this.coverPath = coverPath;
    }

    public Long getLikeCounts() {
        return this.likeCounts;
    }

    public void setLikeCounts(final Long likeCounts) {
        this.likeCounts = likeCounts;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
}
