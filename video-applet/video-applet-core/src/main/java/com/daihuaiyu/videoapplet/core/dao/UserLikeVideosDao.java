package com.daihuaiyu.videoapplet.core.dao;


import com.daihuaiyu.videoapplet.core.domain.UsersLikeVideos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 用户点赞取消点赞DAO接口
 *
 * @author daihuaiyu
 * @create: 2021-04-15 14:28
 **/
public interface UserLikeVideosDao extends JpaRepository<UsersLikeVideos,String>, JpaSpecificationExecutor<UsersLikeVideos> {

}
