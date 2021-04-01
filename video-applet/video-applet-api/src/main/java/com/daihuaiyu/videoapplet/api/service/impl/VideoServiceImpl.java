package com.daihuaiyu.videoapplet.api.service.impl;

import com.daihuaiyu.videoapplet.api.service.VideoService;
import com.daihuaiyu.videoapplet.core.dao.VideoDao;
import com.daihuaiyu.videoapplet.core.domain.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/4/1 23:12
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;
    /**
     * 保存视频信息
     *
     * @param video
     */
    @Override
    public void save(Video video) {
        videoDao.save(video);
    }
}
