package com.daihuaiyu.videoapplet.core.dao;

import com.daihuaiyu.videoapplet.core.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 评论回复接口
 *
 * @author daihuaiyu
 * @create: 2021-04-15 16:45
 **/
@Repository
public interface CommentsDao extends JpaRepository<Comments,String>, JpaSpecificationExecutor<Comments> {

}
