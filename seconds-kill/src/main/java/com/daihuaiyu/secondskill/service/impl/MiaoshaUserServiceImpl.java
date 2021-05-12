package com.daihuaiyu.secondskill.service.impl;

import com.alibaba.fastjson.JSON;
import com.daihuaiyu.secondskill.config.CodeEnum;
import com.daihuaiyu.secondskill.controller.LoginController;
import com.daihuaiyu.secondskill.dao.MiaoshaUserMapper;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.exception.GlobalException;
import com.daihuaiyu.secondskill.redis.MiaoshaUserKey;
import com.daihuaiyu.secondskill.service.MiaoshaUserService;
import com.daihuaiyu.secondskill.util.Md5Util;
import com.daihuaiyu.secondskill.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 登录服务
 * 问题：token放在cookie中和放在请求头中的区别
 * cookie中: 防止xss攻击,但是导致所有请求都会携带token
 * 请求头中: 会有xss风险,而且前端需要保存token并在每次请求的时候携带…好处是可以控制哪些请求携带,哪些不需要携带
 * @ClassName: MiaoshaUserServiceImpl
 * @Author: hydai
 * @Date: 2021/5/10 22:50
 * @Description:
 */
@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    private MiaoshaUserMapper miaoshaUserMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public MiaoshaUser getByToken(HttpServletResponse response,String token) throws IOException {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        logger.info("秒杀key为："+MiaoshaUserKey.token.getPrefix());
        MiaoshaUser miaoshaUser = JSON.parseObject((String) redisTemplate.opsForHash().get(MiaoshaUserKey.token.getPrefix()+token, token),MiaoshaUser.class);
        return miaoshaUser;
    }

    @Override
    public boolean login (HttpServletResponse response, LoginVo loginVo) {
        if(loginVo ==null){
            throw  new GlobalException(CodeEnum.SERVER_ERROR);
        }
        MiaoshaUser user = miaoshaUserMapper.getUserById( loginVo.getMobile());
        if(user==null){
            throw new GlobalException(CodeEnum.MOBILE_NOT_EXIST);
        }
        //校验密码
        if(!Md5Util.formPassToDBPass(loginVo.getPassword()).equals(user.getPassword())){
            throw new GlobalException(CodeEnum.PASSWORD_ERROR);
        }
        //生成token
        String token = UUID.randomUUID().toString().replace("-", "");
        //添加到cookie
        addCookie(response,token,user);
        return true;
    }

    private void addCookie(HttpServletResponse response,  String token,MiaoshaUser user) {
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        String prefix = MiaoshaUserKey.token.getPrefix() + token;
            //过期时间小于等于0则不设置过期时间
            if (MiaoshaUserKey.token.expireSeconds() <= 0) {
                opsForHash.put(prefix, token, JSON.toJSONString(user));
            } else { //获取时间大于0设置key的过期时间

                opsForHash.put(prefix, token, JSON.toJSONString(user));
                redisTemplate.expire(prefix, MiaoshaUserKey.token.expireSeconds(), TimeUnit.SECONDS);
            }
        Cookie cookie = new Cookie(MiaoshaUserServiceImpl.COOKI_NAME_TOKEN,token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
