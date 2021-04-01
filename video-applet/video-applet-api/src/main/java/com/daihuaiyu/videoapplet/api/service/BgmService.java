package com.daihuaiyu.videoapplet.api.service;

import com.daihuaiyu.videoapplet.core.domain.Bgm;

import java.util.List;

/**
 * 背景音乐相关接口interface
 *
 * @author daihuaiyu
 * @create: 2021-03-31 10:30
 **/
public interface BgmService {

    /**查询背景音乐列表*/
    List<Bgm> findAllBgm();

    Bgm findOne(String bgmId);
}
