package com.daihuaiyu.secondskill.service;

import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 秒杀服务
 * @Interface: MiaoshaUserService
 * @Author: hydai
 * @Date: 2021/5/10 22:44
 * @Description:
 */
public interface MiaoshaUserService {

    /**根据手机号判断用户是否存在*/
    boolean login(HttpServletResponse response, LoginVo loginVo);

    MiaoshaUser getByToken(HttpServletResponse response, String token) throws IOException;
}
