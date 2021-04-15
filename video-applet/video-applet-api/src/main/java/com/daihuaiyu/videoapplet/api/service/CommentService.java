package com.daihuaiyu.videoapplet.api.service;

import com.daihuaiyu.videoapplet.api.util.PageResult;
import com.daihuaiyu.videoapplet.core.domain.Comments;

/**
 * 评论回复Service
 *
 * @author daihuaiyu
 * @create: 2021-04-15 16:48
 **/
public interface CommentService {

    /**保存评论信息*/
    void saveComment(Comments comments);

    /**分页查询所有的评论*/
    PageResult findComments(final String videoId, Integer page, Integer size);
}
