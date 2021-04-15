package com.daihuaiyu.videoapplet.api.service.impl;

import com.daihuaiyu.videoapplet.api.service.CommentService;
import com.daihuaiyu.videoapplet.api.util.IdUtil;
import com.daihuaiyu.videoapplet.api.util.PageResult;
import com.daihuaiyu.videoapplet.api.util.TimeAgoUtil;
import com.daihuaiyu.videoapplet.core.dao.CommentsDao;
import com.daihuaiyu.videoapplet.core.dao.UserDao;
import com.daihuaiyu.videoapplet.core.domain.Comments;
import com.daihuaiyu.videoapplet.core.domain.CommentsVo;
import com.daihuaiyu.videoapplet.core.domain.Users;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 评论回复Service实现
 * 1.保存评论：
 * 前端传入Comments实体类中的相关参数，通过service的保存方法，将留言保存到数据库即可
 * 根据Comments的参数数量来判断用户是留言还是为回复留言
 * 2.分页查询所有评论：
 * 对该视频的所有评论进行查询，查询结果需要进行分页，需要前端将评论的页数以及视频的id作为参数传入，
 * 在controller层定义每页显示的页数为4，将查询到的结果已vo包装类的形式返回，
 * 在前端需要显示评论者的昵称，评论者的头像，以及多长时间前评论或回复的，这里我们需要封装一个时间的工具类对时间进行一下统一的处理，在vo类中加上一个新的显示时间的字段，
 * 用来存放我们处理后的时间数据，将用户的头像信息用户的昵称都作为新的字段加入vo类中，以pageResult的形式返回给前端
 * @author daihuaiyu
 * @create: 2021-04-15 16:53
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentsDao commentsDao;

    @Autowired
    private UserDao userDao;
    /**
     * 保存评论信息
     *
     * @param comments
     */
    @Override
    @Transactional
    public void saveComment(Comments comments) {
        comments.setId(IdUtil.getId());
        comments.setCreateTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        commentsDao.save(comments);
    }

    /**
     * 分页查询所有的评论
     *
     * @param videoId
     * @param page
     * @param size
     */
    @Override
    public PageResult findComments(String videoId, Integer page, Integer size) {
        //创建返回给前端的分页结果类
        PageResult pageResult = new PageResult();
        //创建分页的条件
        Pageable pageable = PageRequest.of(page-1,size);
        List<CommentsVo> rows = new ArrayList<>();
        Page<Comments> commentsPage = commentsDao.findAll((Specification<Comments>) (root, cq, cb) -> {
            //根据视频的id分页查询数据，并按照创建时间降序排列
            Predicate p1 = cb.equal(root.get("videoId").as(String.class), videoId);
            //通过这个条件进行查询
            cq.where(p1);
            //通过创建实现对查询结果进行降序排列
            cq.orderBy(cb.desc(root.get("createTime").as(Date.class)));
            return cq.getRestriction();
        }, pageable);
        pageResult.setPage(page);
        pageResult.setTotalElements(commentsPage.getTotalElements());
        commentsPage.forEach( Comments->{
            CommentsVo commentsVo = new CommentsVo();
            commentsVo.setTimeAgo(TimeAgoUtil.format(Comments.getCreateTime()));
            //查询评论者的相关用户信息
            Optional<Users> users = userDao.findById(Comments.getFromUserId());
            //如果getToUserId不为空，说明有用户对该条评论做出了回复
            if(!StringUtils.isBlank(Comments.getToUserId())){
                //查询到被回复对象的信息
                Optional<Users> one1 = userDao.findById(Comments.getToUserId());
                commentsVo.setToNickname(one1.get().getNickname());
            }
                    //将用户的头像信息存放到vo中
                    commentsVo.setFaceImage(users.get().getFaceImage());
                    //将用户的昵称存放到vo中
                    commentsVo.setNickname(users.get().getNickname());
                    //将查询到的comments类拷贝到vo对象中
                    BeanUtils.copyProperties(users.get(),commentsVo);
                    rows.add(commentsVo);
                }

        );
        pageResult.setRows(rows);
        return pageResult;
    }
}

