package com.daihuaiyu.videoapplet.api.service;

import com.daihuaiyu.videoapplet.api.util.PageResult;
import com.daihuaiyu.videoapplet.core.domain.Video;

/**
 * 视频信息相关接口
 *
 * @author daihuaiyu
 * @create: 2021-03-31 10:30
 **/
public interface VideoService {

    /**保存视频信息*/
    void save(Video video);

    /**分页查询视频信息*/
    PageResult findAllVideo(Integer pageNum,Integer pageSize,String searchValue);
}
