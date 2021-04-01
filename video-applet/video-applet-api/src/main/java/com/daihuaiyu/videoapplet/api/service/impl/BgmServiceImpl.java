package com.daihuaiyu.videoapplet.api.service.impl;

import com.daihuaiyu.videoapplet.api.service.BgmService;
import com.daihuaiyu.videoapplet.core.dao.BgmDao;
import com.daihuaiyu.videoapplet.core.domain.Bgm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/4/1 21:21
 */
@Service
public class BgmServiceImpl implements BgmService {


    @Autowired
    private BgmDao bgmDao;
    /**
     * 查询背景音乐列表
     */
    @Override
    public List<Bgm> findAllBgm() {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return bgmDao.findAll(sort);
    }

    @Override
    public Bgm findOne(String bgmId) {
        return bgmDao.findById(bgmId).get();
    }
}
