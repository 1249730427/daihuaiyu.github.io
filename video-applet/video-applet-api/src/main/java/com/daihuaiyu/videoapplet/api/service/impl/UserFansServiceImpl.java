package com.daihuaiyu.videoapplet.api.service.impl;

import com.daihuaiyu.videoapplet.api.service.UserFansService;
import com.daihuaiyu.videoapplet.api.util.IdUtil;
import com.daihuaiyu.videoapplet.core.dao.UserDao;
import com.daihuaiyu.videoapplet.core.dao.UsersFansDao;
import com.daihuaiyu.videoapplet.core.domain.Users;
import com.daihuaiyu.videoapplet.core.domain.UsersFans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * 用户关注取消关注Service实现
 *
 * @author daihuaiyu
 * @create: 2021-04-19 15:14
 **/
@Service
public class UserFansServiceImpl implements UserFansService {

    @Autowired
    private UsersFansDao usersFansDao;

    @Autowired
    private UserDao userDao;

    /**
     * 用户关注：
     *
     * @param id
     * @param fansId
     */
    @Override
    public void follow(String id, String fansId) {
        UsersFans usersFans = new UsersFans();
        usersFans.setId(IdUtil.getId());
        usersFans.setUserId(id);
        usersFans.setFanId(fansId);
        usersFansDao.save(usersFans);
        Optional<Users> followUsers = userDao.findById(id);
        if(followUsers.isPresent()){
            followUsers.get().setFollowCounts(followUsers.get().getFollowCounts()+1);
            userDao.save(followUsers.get());
        }
        Optional<Users> fanUsers = userDao.findById(fansId);
        followUsers.get().setFansCounts(followUsers.get().getFansCounts()+1);
        userDao.save(fanUsers.get());

    }

    @Override
    public void unfollow(String id, String fansId) {
        UsersFans usersFans = this.findByUserIdAndFansId (id,fansId).get(0);
        usersFansDao.deleteById(usersFans.getId());

        Optional<Users> users = userDao.findById(id);
        users.get().setFansCounts(users.get().getFansCounts()-1);
        userDao.save(users.get());

        Optional<Users> optional = userDao.findById(fansId);
        optional.get().setFollowCounts(optional.get().getFansCounts()-1);
        userDao.save(optional.get());
    }

    @Override
    public Boolean findIsFollowed(String id, String fansId) {
        List<UsersFans> usersFans = this.findByUserIdAndFansId(id, fansId);
        if(usersFans !=null && usersFans.size()>0){
            return true;
        }
        return false;
    }
    /**
     * 根基用户id个粉丝id精确查询用户和粉丝关系
     * @param id
     * @param fansId
     * @return
     */
    @Transactional
    List<UsersFans> findByUserIdAndFansId(final String id, final String fansId) {
        return usersFansDao.findAll((Specification<UsersFans>) (root, cq, cb) -> {
            Predicate p1 = cb.equal(root.get("userId").as(String.class), id);
            Predicate p2 = cb.equal(root.get("fanId").as(String.class), fansId);
            Predicate[] predicate = new Predicate[]{p1, p2};
            return cb.and(predicate);
        });
    }
}

