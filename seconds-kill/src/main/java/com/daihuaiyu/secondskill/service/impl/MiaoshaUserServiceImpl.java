package com.daihuaiyu.secondskill.service.impl;

import com.daihuaiyu.secondskill.config.CodeEnum;
import com.daihuaiyu.secondskill.dao.MiaoshaUserMapper;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.exception.GlobalException;
import com.daihuaiyu.secondskill.service.MiaoshaUserService;
import com.daihuaiyu.secondskill.util.Md5Util;
import com.daihuaiyu.secondskill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: MiaoshaUserServiceImpl
 * @Author: hydai
 * @Date: 2021/5/10 22:50
 * @Description:
 */
@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService {

    @Autowired
    private MiaoshaUserMapper miaoshaUserMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**
     * 根据手机号判断用户是否存在
     *
     * @param mobile
     */
    @Override
    public boolean login (HttpServletResponse response, LoginVo loginVo) {
        MiaoshaUser user = miaoshaUserMapper.getUserById( loginVo.getMobile());
        if(user==null){
            throw new GlobalException(CodeEnum.MOBILE_NOT_EXIST);
        }
        //校验密码
        if(!Md5Util.formPassToDBPass(loginVo.getPassword()).equals(user.getPassword())){
            throw new GlobalException(CodeEnum.PASSWORD_ERROR);
        }
        return true;
    }
}
