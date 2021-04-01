package com.daihuaiyu.videoapplet.core.dao;

import com.daihuaiyu.videoapplet.core.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 视频信息DAO
 *
 * @author :daihuaiyu
 * @Description: 视频信息DAO
 * @create 2021/4/1 23:08
 */
@Repository
public interface VideoDao extends JpaRepository<Video,String>, JpaSpecificationExecutor<Video> {
}
