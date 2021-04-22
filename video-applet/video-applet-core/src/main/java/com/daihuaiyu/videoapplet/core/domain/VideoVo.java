package com.daihuaiyu.videoapplet.core.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * 视频实体类
 *
 * @author :daihuaiyu
 * @Description: 视频实体类
 * @create 2021/4/1 22:48
 */
@Data
public class VideoVo {

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", name = "faceImage", example = "123", required = true)
    private String faceImage;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", name = "nickmane", example = "123", required = true)
    private String nickmane;

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

    public Date getCreateTime() {
        if(createTime ==null){
            return null;
        }
        return (Date) createTime.clone();
    }

    public void setCreateTime(Date createTime) {
        if(createTime ==null){
            this.createTime =null;
        }else{
            this.createTime = (Date) createTime.clone();
        }
    }
}
