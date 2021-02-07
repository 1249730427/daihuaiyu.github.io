package com.study.chapter1.controller;

import com.study.chapter1.entity.AccessToken;
import com.study.chapter1.entity.AppInfo;
import com.study.chapter1.entity.TokenInfo;
import com.study.chapter1.entity.UserInfo;
import com.study.chapter1.util.ApiCodeEnum;
import com.study.chapter1.util.ApiResponse;
import com.study.chapter1.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.security.interfaces.RSAKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * token获取controller
 * 1.获取接口令牌token：
 * a.校验用户参数 appId、timetamp、sign是否为空
 * b.校验时间：防止DOS攻击
 * c.验签：校验sign(sign由appId+timetamp+securityKey 进行MD5加密得到)
 *
 * 2.获取用户令牌token：
 * @author daihuaiyu
 * @create: 2021-02-07 13:45
 **/
@Slf4j
@Controller
@RequestMapping(path = "/api/token")
public class TokenController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(path = "/api_toke")
    public ApiResponse<AccessToken> apiToken(String appId, @RequestHeader("timestamp") String timestamp, @RequestHeader("sign") String sign){
        if(StringUtils.isEmpty(appId) || StringUtils.isEmpty(timestamp)|| StringUtils.isEmpty(sign)){
            return ApiResponse.error(ApiCodeEnum.PARAMETER_ERROR.getMsg(),ApiCodeEnum.PARAMETER_ERROR.getMsg());
        }
        if(System.currentTimeMillis()-Long.parseLong(timestamp)>5*60*1000){
            return ApiResponse.error(ApiCodeEnum.TOKEN_EXPIRE.getCode(),ApiCodeEnum.TOKEN_EXPIRE.getMsg());
        }
        AppInfo appInfo = new AppInfo("1","12345678954556");

        String signString= appId+timestamp+appInfo.getSecurityKey();
        String encode = MD5Util.encode(signString);
        if(!sign.equalsIgnoreCase(encode)){
            return ApiResponse.error(ApiCodeEnum.SIGN_ERROR.getCode(),ApiCodeEnum.SIGN_ERROR.getMsg());
        }

        AccessToken accessToken = this.saveToken(0, appInfo, null);

        return ApiResponse.success(accessToken);
    }

    @RequestMapping(path = "/user_toke")
    public ApiResponse<AccessToken> userToken(@RequestParam("userName") String userName, @RequestParam("password") String password){
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            return ApiResponse.error(ApiCodeEnum.PARAMETER_ERROR.getMsg(),ApiCodeEnum.PARAMETER_ERROR.getMsg());
        }
        // 根据用户名查询密码, 并比较密码(密码在传输过程中可以使用RSA加密一下)
        UserInfo userInfo = new UserInfo(userName,"81255cb0dca1a5f304328a70ac85dcbd", "111111");
        String pwd = password+userInfo.getSalt();
        if(!MD5Util.encode(pwd).equalsIgnoreCase(userInfo.getPassword())){
            return ApiResponse.error(ApiCodeEnum.PASSWORD_ERROR.getCode(),ApiCodeEnum.PASSWORD_ERROR.getMsg());
        }

        AppInfo appInfo = new AppInfo("1","12345678954556");

        AccessToken accessToken = this.saveToken(1, appInfo, userInfo);

        userInfo.setAccessToken(accessToken);

        return ApiResponse.success(userInfo);
    }

    public AccessToken saveToken(int type, AppInfo appInfo, UserInfo userInfo){

        TokenInfo tokenInfo = new TokenInfo();

        tokenInfo.setType(0); //0.api_token  1.user_token

        tokenInfo.setAppInfo(appInfo);

        if(type ==2){

            tokenInfo.setUserInfo(userInfo);
        }

        String token = UUID.randomUUID().toString();

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());

        calendar.add(Calendar.SECOND,7200);

        Date expireTime =calendar.getTime();

        ValueOperations valueOperations = redisTemplate.opsForValue();

        //有效期为2个小时
        valueOperations.set(token,tokenInfo,7200, TimeUnit.SECONDS);

        AccessToken accessToken = new AccessToken(token,expireTime);

        log.info("token="+token+"过期时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mi:ss").format(expireTime));

        return accessToken;
    }

    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        String signString = timestamp + "1" + "12345678954556";
        String sign = MD5Util.encode(signString);
        System.out.println(sign);

        System.out.println("-------------------");
        signString = "password=123456&username=1&12345678954556" + "ff03e64b-427b-45a7-b78b-47d9e8597d3b1529815393153sdfsdfsfs" + timestamp + "A1scr6";
        sign = MD5Util.encode(signString);
        System.out.println(sign);
    }


}

