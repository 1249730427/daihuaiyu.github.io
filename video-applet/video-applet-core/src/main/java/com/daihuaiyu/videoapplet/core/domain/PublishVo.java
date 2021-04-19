package com.daihuaiyu.videoapplet.core.domain;

import lombok.Data;

/**
 * 视频发布者信息包装类
 *
 * @author daihuaiyu
 * @create: 2021-04-19 16:20
 **/
@Data
public class PublishVo {

    private Users users;

    private Boolean isLike;
}

