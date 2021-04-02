package com.daihuaiyu.videoapplet.api.service.impl;

import com.daihuaiyu.videoapplet.api.service.VideoService;
import com.daihuaiyu.videoapplet.api.util.PageResult;
import com.daihuaiyu.videoapplet.core.dao.UserDao;
import com.daihuaiyu.videoapplet.core.dao.VideoDao;
import com.daihuaiyu.videoapplet.core.domain.UserVo;
import com.daihuaiyu.videoapplet.core.domain.Users;
import com.daihuaiyu.videoapplet.core.domain.Video;
import com.daihuaiyu.videoapplet.core.domain.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/4/1 23:12
 */
@Service
public class VideoServiceImpl implements VideoService  {

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private UserDao userDao;
    /**
     * 保存视频信息
     *
     * @param video
     */
    @Override
    public void save(Video video) {
        videoDao.save(video);
    }

    @Override
    public PageResult findAllVideo(Integer pageNum, Integer pageSize,String searchValue) {

        PageResult pageResult = new PageResult();
        Page<Video> videoPage =null;
        List<VideoVo> videoVoList = new ArrayList<>();
        //对搜索的关键字进行查询，如果数据库中没有，就新添加一条数据，如果有，就在num上做+1的操作
        if(!StringUtils.isEmpty(searchValue)){
            //将当前页数和每页要查的数据量传入,并根据创建时间降序排列
            Pageable pageable =PageRequest.of(pageNum,pageSize,Sort.by(Sort.Direction.DESC,"createTime"));
             videoPage = videoDao.findAll(pageable);
        }
        //将当前为第几页传入分页结果中
        pageResult.setPage(pageNum);
        //传入总页数
        pageResult.setTotalPage(videoPage.getTotalPages());
        //传入总记录数
        pageResult.setTotalElements(videoPage.getTotalElements());
        //通过对page中的对象进行遍历，做数据收集，最后返回videosVoList
        videoPage.stream().forEach(video -> {
            Users users = userDao.findById(video.getUserId()).get();
            VideoVo videoVor = new VideoVo();
            BeanUtils.copyProperties(users,videoVor);
            BeanUtils.copyProperties(video,videoVor);
            videoVoList.add(videoVor);
        });
        //将videosVoList存入分页结果中
        pageResult.setRows(videoVoList);
        return pageResult;
    }
}
