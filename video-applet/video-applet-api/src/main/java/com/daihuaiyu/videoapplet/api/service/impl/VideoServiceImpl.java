package com.daihuaiyu.videoapplet.api.service.impl;

import com.daihuaiyu.videoapplet.api.service.VideoService;
import com.daihuaiyu.videoapplet.api.util.IdUtil;
import com.daihuaiyu.videoapplet.api.util.PageResult;
import com.daihuaiyu.videoapplet.core.dao.HotDao;
import com.daihuaiyu.videoapplet.core.dao.UserDao;
import com.daihuaiyu.videoapplet.core.dao.VideoDao;
import com.daihuaiyu.videoapplet.core.domain.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 视频Service实现
 *
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

    @Autowired
    private HotDao hotDao;
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
    public PageResult findAllVideo(Integer pageNum, Integer pageSize,final String searchValue) {

        PageResult pageResult = new PageResult();
        Page<Video> videoPage = null;
        List<VideoVo> videoVoList = new ArrayList<>();
        //对搜索的关键字进行查询，如果数据库中没有，就新添加一条数据，如果有，就在num上做+1的操作
        if(!StringUtils.isEmpty(searchValue)){
            Hot hot = hotDao.findByContent(searchValue);
            //如果返回值为空，则说明该关键字没有被人搜索过，所以将该关键字添加到数据库，并将搜索次数设置为1
            if(hot ==null){
                Hot hot1 = new Hot();
                hot1.setId(IdUtil.getId());
                hot1.setContent(searchValue);
                hot1.setNum(1L);
                hotDao.save(hot1);
            }
            //如果返回结果不为空，则说明该关键字已经被搜索过，在该关键字的搜索次数字段自增1，并保存
            if(hot!=null){
                hot.setNum(hot.getNum()+1);
                hotDao.save(hot);
            }
            Pageable pageable = PageRequest.of(pageNum-1,pageSize);

            //通过视频描述模糊查询视频，返回分页结果
            videoPage= findAllVideosByVideoDesc(searchValue,pageable);
        }
        if(StringUtils.isEmpty(searchValue)){
            //将当前页数和每页要查的数据量传入,并根据创建时间降序排列
            Pageable pageable =PageRequest.of(pageNum-1,pageSize,Sort.by(Sort.Direction.DESC,"createTime"));
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

    /**
     * 查询热搜词功能
     */
    @Override
    public List<String> findHot() {
        List<String> hots = new ArrayList<>();
        Sort sort = Sort.by(Sort.Direction.DESC,"num");
        Pageable pageable = PageRequest.of(0,5,sort);
        Page<Hot> hotPage = hotDao.findAll(pageable);
        hotPage.forEach(hot -> hots.add(hot.getContent()));
        return hots;
    }

    private Page<Video> findAllVideosByVideoDesc(String searchValue, Pageable pageable) {

        Page<Video> videoPage = videoDao.findAll((Specification<Video>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.like(root.get("searchValue").as(String.class), "%"+searchValue+"%");
            criteriaQuery.where(predicate);
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("videoDesc").as(String.class)));
            return predicate;
        }, pageable);
        return videoPage;
    }
}
