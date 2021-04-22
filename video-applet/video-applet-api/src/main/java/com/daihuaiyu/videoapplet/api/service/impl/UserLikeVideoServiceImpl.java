package com.daihuaiyu.videoapplet.api.service.impl;

import com.daihuaiyu.videoapplet.api.service.UserLikeVideoService;
import com.daihuaiyu.videoapplet.api.util.IdUtil;
import com.daihuaiyu.videoapplet.core.dao.UserDao;
import com.daihuaiyu.videoapplet.core.dao.UserLikeVideosDao;
import com.daihuaiyu.videoapplet.core.dao.VideoDao;
import com.daihuaiyu.videoapplet.core.domain.Users;
import com.daihuaiyu.videoapplet.core.domain.UsersLikeVideos;
import com.daihuaiyu.videoapplet.core.domain.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.Optional;


/**
 * 用户点赞取消点赞Service实现
 * 1.用户对该视频点赞后，会将该用户的id和被点赞的视频的id添加到users_like_videos表，
 * 并且根据videoId，去对videos表中该视频的like_counts字段加1，
 * 2.根据该videoId查询到该视频发布者的userid，并将receive_like_counts字段进行+1操作，
 * 以上操作就完成了用户对该视频点赞
 *
 * @author daihuaiyu
 * @create: 2021-04-15 14:38
 **/
@Slf4j
@Service
public class UserLikeVideoServiceImpl implements UserLikeVideoService {

    @Autowired
    private UserLikeVideosDao userLikeVideosDao;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private UserDao userDao;

    /**
     * 用户点赞接口
     *
     * @param id
     * @param videoId
     * @param videoCreateId
     */
    @Override
    @Transactional
    public void like(String id, String videoId, String videoCreateId) {
        UsersLikeVideos usersLikeVideos = new UsersLikeVideos();
        usersLikeVideos.setId(IdUtil.getId());
        usersLikeVideos.setUserId(id);
        usersLikeVideos.setVideoId(videoId);
        userLikeVideosDao.save(usersLikeVideos);
        //根据videoId查询视频，并将该视频的喜欢次数+1
        Optional<Video> video = videoDao.findById(videoId);
        if (video.isPresent()) {
            video.get().setLikeCounts(video.get().getLikeCounts() + 1);
            videoDao.save(video.get());
        }
        //根据视频发布者的id查询发布者信息，并将点赞数+1
        Optional<Users> users = userDao.findById(videoCreateId);
        if (users.isPresent()) {
            users.get().setReceiveLikeCounts(users.get().getReceiveLikeCounts() + 1);
            userDao.save(users.get());
        }

    }

    /**
     * 用户取消点赞接口
     *
     * @param id
     * @param videoId
     * @param videoCreateId
     */
    @Override
    @Transactional
    public void unlike(String id, String videoId, String videoCreateId) {
        UsersLikeVideos usersLikeVideos = this.findByUserIdAndVideoId(id, videoId);
        userLikeVideosDao.delete(usersLikeVideos);
        //根据videoId查询视频，并将该视频的喜欢次数-1
        Optional<Video> video = videoDao.findById(videoId);
        if (video.isPresent()) {
            video.get().setLikeCounts(video.get().getLikeCounts() - 1);
            videoDao.save(video.get());
        }
        //根据视频发布者的id查询发布者信息，并将点赞数-1
        Optional<Users> users = userDao.findById(videoCreateId);
        if (users.isPresent()) {
            users.get().setReceiveLikeCounts(users.get().getReceiveLikeCounts() - 1);
            userDao.save(users.get());
        }
    }

    /**
     * 根据用户ID和视频ID找到用户点赞信息
     *
     * @param id
     * @param videoId
     */
    @Override
    public UsersLikeVideos findByUserIdAndVideoId(String id, String videoId) {
        return userLikeVideosDao.findAll((Specification<UsersLikeVideos>) (root, cq, cb) -> {
            //将usersLikeVideos实体类中的userid获取，类型为String，并将id的值赋值给他，作为第一个查询条件
            Predicate p1 = cb.equal(root.get("userId").as(String.class), id);
            //将usersLikeVideos实体类中的videoId获取，类型为String，并将videoId的值赋值给他，作为第二个查询条件
            Predicate p2 = cb.equal(root.get("videoId").as(String.class), videoId);
            //将这两个条件放到一个数组中
            Predicate[] predicate = new Predicate[]{p1, p2};
            //最后就根据数组中的这两个条件进行插叙，实现多条件查询
            return cb.and(predicate);
        }).get(0);
    }

    /**
     * 查询用户是否点赞了视频信息
     *
     * @param id
     * @param videoId
     */
    @Override
    public boolean userIsLike(String id, String videoId) {
        UsersLikeVideos likeVideos = this.findByUserIdAndVideoId(id, videoId);
        if (likeVideos != null) {
            return true;
        }
        return false;
    }
}

